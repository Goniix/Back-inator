package servlet.model;

import java.sql.Date;

public record Partie(int pno, int jno1, int jno2, Date date, String status, String temps, int gagnant) {

}
