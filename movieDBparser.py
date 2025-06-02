import csv
import mysql.connector
import pandas as pd

# Database connection (fill in the specific server information)
db = mysql.connector.connect(
    host="",
    user="",
    passwd="",
    database='movie'
)

mycursor = db.cursor()

# Helper functions

# Converts runtime in hours and minutes format to total minutes.
def convert_run_time(run_time):
    if isinstance(run_time, str):
        hours, minutes = 0, 0
        if 'h' in run_time:
            hours = int(run_time.split('h')[0].strip())
        if 'm' in run_time:
            minutes = int(run_time.split('m')[0].split()[-1].strip())
        return hours * 60 + minutes
    return run_time

# Fetches the next available director ID.
def get_next_director_id():
    sql = "SELECT MAX(directorID) FROM Director"
    mycursor.execute(sql)
    result = mycursor.fetchone()
    return result[0] + 1 if result[0] is not None else 1


# Inserts unique certificates from the data into the Certificate table.
def insert_certificates():
    with open('data.csv', 'r') as file:
        csv_reader = csv.reader(file)
        for row in csv_reader:
            if row[5] != "certificate":
                certificate_name = row[5]
                certificate_id = {'G': 1, 'PG': 2, 'PG-13': 3, 'R': 4, '18+': 5}.get(certificate_name)
                if certificate_id:
                    mycursor.execute("SELECT certificateID FROM Certificate WHERE certificateID = %s", (certificate_id,))
                    if not mycursor.fetchone():
                        mycursor.execute("INSERT INTO Certificate (certificateID, certificateName) VALUES (%s, %s)", (certificate_id, certificate_name))
                        db.commit()

# Inserts unique directors from the data into the Director table.
def insert_directors():
    arr = []
    with open('data.csv', 'r') as file:
        csv_reader = csv.reader(file)
        for row in csv_reader:
            if row[10] != "directors" and row[10] not in arr:
                sql = "SELECT directorID FROM Director WHERE directorName = %s"
                mycursor.execute(sql, (row[10],))
                result = mycursor.fetchone()
                if not result:
                    director_id = get_next_director_id()
                    mycursor.execute("INSERT INTO Director (directorID, directorName) VALUES (%s, %s)", (director_id, row[10]))
                    db.commit()
                arr.append(row[10])

# Inserts movie data into the Movie table.
def insert_movies():
    with open('data.csv', 'r') as file:
        csv_reader = csv.reader(file)
        for row in csv_reader:
            if row[0] != "rank":
                tagline = row[7][:255]  # Truncate tagline to 255 chars
                sql = "INSERT INTO Movie (movieId, movieName, movieYear, rating, run_time, tagline, budget, box_office) VALUES (%s, %s, %s, %s, %s, %s, %s, %s)"
                val = (row[0], row[1], row[2], row[3], convert_run_time(row[6]), tagline, row[8], row[9])
                mycursor.execute(sql, val)

                certificate_id = {'G': 1, 'PG': 2, 'PG-13': 3, 'R': 4, '18+': 5}.get(row[5])
                if certificate_id:
                    sql1 = "UPDATE Movie SET certificateID = %s WHERE movieId = %s"
                    mycursor.execute(sql1, (certificate_id, row[0]))
                    db.commit()
                    
# Inserts genres from the data into the Genre table.
def insert_genres():
    df = pd.read_csv('data.csv')
    unique_genres = {genre.strip() for genres in df['genre'].dropna() for genre in genres.split(',')}
    genre_id_map = {genre: idx + 1 for idx, genre in enumerate(sorted(unique_genres))}
    
    for genre, genre_id in genre_id_map.items():
        mycursor.execute("""
            INSERT INTO Genre (genreID, genreName)
            VALUES (%s, %s)
            ON DUPLICATE KEY UPDATE genreName = VALUES(genreName)
        """, (genre_id, genre))
        db.commit()

# Inserts movie-genre relations into the MovieGenre table.
def insert_movie_genre():
    with open('data.csv', 'r') as file:
        csv_reader = csv.DictReader(file)
        for row in csv_reader:
            movie_id = int(row['rank'])
            genres = row['genre'].strip().split(',')
            for genre_name in genres:
                genre_name = genre_name.strip()
                mycursor.execute("SELECT genreID FROM Genre WHERE genreName = %s", (genre_name,))
                genre_result = mycursor.fetchone()
                if genre_result:
                    genre_id = genre_result[0]
                    mycursor.execute("SELECT 1 FROM MovieGenre WHERE movieID = %s AND genreID = %s", (movie_id, genre_id))
                    if not mycursor.fetchone():
                        mycursor.execute("INSERT INTO MovieGenre (movieID, genreID) VALUES (%s, %s)", (movie_id, genre_id))
                        db.commit()

# Inserts movie-director relations into the MovieDirector table.
def insert_movie_director():
    with open('data.csv', 'r') as file:
        csv_reader = csv.DictReader(file)
        for row in csv_reader:
            movie_id = int(row['rank'])
            director_name = row['directors'].strip()
            mycursor.execute("SELECT directorID FROM Director WHERE directorName = %s", (director_name,))
            director_result = mycursor.fetchone()
            if director_result:
                director_id = director_result[0]
                mycursor.execute("SELECT 1 FROM MovieDirector WHERE movieID = %s AND directorID = %s", (movie_id, director_id))
                if not mycursor.fetchone():
                    mycursor.execute("INSERT INTO MovieDirector (movieID, directorID) VALUES (%s, %s)", (movie_id, director_id))
                    db.commit()

# Run the functions to insert data
insert_certificates()
insert_directors()
insert_movies()
insert_genres()
insert_movie_genre()
insert_movie_director()

# Close cursor and connection
mycursor.close()
db.close()