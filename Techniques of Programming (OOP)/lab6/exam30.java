public class exam30{
    public static void main(String[] args){
        In candidateFile = new In("candidates.txt");
        
        int numCandidates = 5;
        
        String[] candidates = new String[numCandidates];
        for(int i = 0; i < numCandidates; i++){
            candidates[i] = candidateFile.readString();
        }

        candidateFile.close();
    }
}