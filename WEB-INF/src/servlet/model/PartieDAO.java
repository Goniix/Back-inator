package servlet.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import utils.DS;

public class PartieDAO extends DAO<Partie> {

    public PartieDAO(DS mng) {
        super(mng);
    }

    public List<Partie> findAll() {
        ArrayList<Partie> imported = new ArrayList<>();
        try (Connection con = manager.getCon()) {
            String query = "select * from partie;";
            PreparedStatement stm = con.prepareStatement(query);
            ResultSet res = stm.executeQuery();

            while (res.next()) {
                imported.add(new Partie(
                        res.getInt("pno"),
                        res.getInt("jno1"),
                        res.getInt("jno2"),
                        res.getDate("date"),
                        res.getString("statut"),
                        res.getString("temps"),
                        res.getInt("gagnant")));
            }

        } catch (SQLException error) {
            System.out.println(error.getMessage());
        } /*
           * catch (NumberFormatException error) {
           * System.out.println("arg 0 must be a int");
           * } catch (ArrayIndexOutOfBoundsException e) {
           * System.out.println("At least 1 arg needed");
           * }
           */
        return imported;
    }

    public Partie findById(int id) {
        Partie imported = null;
        try (Connection con = manager.getCon()) {
            String query = "select * from partie where pno = ?;";
            PreparedStatement stm = con.prepareStatement(query);
            stm.setInt(1, id);
            ResultSet res = stm.executeQuery();

            if (res.next()) {
                System.out.println("partie found");

                imported = new Partie(
                        res.getInt("pno"),
                        res.getInt("jno1"),
                        res.getInt("jno2"),
                        res.getDate("date"),
                        res.getString("statut"),
                        res.getString("temps"),
                        res.getInt("gagnant"));
            } else {
                System.out.println("partie not found");
            }

        } catch (SQLException error) {
            System.out.println(error.getMessage());
        } /*
           * catch (NumberFormatException error) {
           * System.out.println("arg 0 must be a int");
           * } catch (ArrayIndexOutOfBoundsException e) {
           * System.out.println("At least 1 arg needed");
           * }
           */
        return imported;
    }

    @Override
    public void add(Partie item) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'add'");
    }

    @Override
    public void remove(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'remove'");
    }
}
