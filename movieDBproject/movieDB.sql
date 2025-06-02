create database movie;

use movie;

create table Director(
	directorID int NOT NULL,
    directorName varchar(255) NOT NULL UNIQUE,
    PRIMARY KEY (directorID)
);

create table Genre(
	genreID int NOT NULL,
    genreName varchar(255) NOT NULL UNIQUE,
    PRIMARY KEY (genreID)
);

create table Certificate(
	certificateID int NOT NULL,
    certificateName varchar(255) NOT NULL UNIQUE,
    PRIMARY KEY (certificateID)
);


create table Movie(
	movieID int NOT NULL,
    movieName char(255),
    movieYear varchar(4),
    rating decimal(2,1),
    run_time int,
    tagline varchar(255),
    budget bigint,
    box_office bigint,
    certificateID int,
    PRIMARY KEY (movieID),
    FOREIGN KEY (certificateID) REFERENCES Certificate(certificateID)
);


create table MovieDirector(
	movieID int NOT NULL,
    directorID int NOT NULL,
    PRIMARY KEY (movieID, directorID),
    FOREIGN KEY (movieID) REFERENCES Movie(movieID), 
    FOREIGN KEY (directorID) REFERENCES Director(directorID)
);

create table MovieGenre(
	movieID int NOT NULL,
    genreID int NOT NULL,
    PRIMARY KEY (movieID, genreID),
    FOREIGN KEY (movieID) REFERENCES Movie(movieID),
    FOREIGN KEY (genreID) REFERENCES Genre(genreID)
);

select * from Movie;

