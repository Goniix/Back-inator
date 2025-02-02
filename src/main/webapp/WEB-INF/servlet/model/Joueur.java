package servlet.model;

public record Joueur(int jno, String pseudo, String email, String pwd, int elo) {
    @Override
    public String toString() {
        return "Joueur{" +
                "jno=" + jno +
                ", pseudo='" + pseudo + '\'' +
                ", email='" + email + '\'' +
                ", elo=" + elo +
                '}';
    }

}
