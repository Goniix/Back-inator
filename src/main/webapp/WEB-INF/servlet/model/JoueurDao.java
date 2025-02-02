package servlet.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import utils.DS;

public class JoueurDao extends DAO<Joueur> {
    public JoueurDao(DS mng) {
        super(mng);
    }

    @Override
    public List<Joueur> findAll() {
        List<Joueur> res = new ArrayList<>();
        try (Connection con = manager.getCon()) {
            PreparedStatement stm = con.prepareStatement("select * from joueur;");
            ResultSet set = stm.executeQuery();
            while (set.next()) {
                Joueur joueur = new Joueur(
                        set.getInt("jno"),
                        set.getString("pseudo"),
                        set.getString("email"),
                        set.getString("pwd"),
                        set.getInt("elo"));
                res.add(joueur);
            }
        } catch (SQLException error) {
            System.out.println(error.getMessage());
        }
        return res;
    }

    public void delete(int id) {

    }

    @Override
    public Joueur findById(int id) {
        Joueur imported = null;
        try (Connection con = manager.getCon()) {
            String query = "select * from joueur where jno = ?;";
            PreparedStatement stm = con.prepareStatement(query);
            stm.setInt(1, id);
            ResultSet res = stm.executeQuery();

            if (res.next()) {
                System.out.println("joueur found");

                imported = new Joueur(
                        res.getInt("jno"),
                        res.getString("pseudo"),
                        res.getString("email"),
                        res.getString("pwd"),
                        res.getInt("elo"));
            } else {
                System.out.println("joueur not found");
            }

        } catch (SQLException error) {
            System.out.println(error.getMessage());
        } catch (NumberFormatException error) {
            System.out.println("arg 0 must be a string");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("At least 1 arg needed");
        }
        return imported;
    }

    @Override
    public void add(Joueur item) {
        try (Connection con = manager.getCon()) {
            String update = "insert into joueur values (" + item.jno() + ",'" + item.pseudo() + "', '" + item.email()
                    + "', '" + item.pwd() + "', " + item.elo() + ")";
            PreparedStatement stm = con.prepareStatement(update);
            // stm.setString(1,);
            // stm.setString(2,);
            // stm.setString(3,);
            // stm.setInt(4,);
            stm.executeUpdate();

        } catch (SQLException error) {
            System.out.println(error.getMessage());
        }
    }

    @Override
    public void remove(int id) {
        try (Connection con = manager.getCon()) {
            PreparedStatement stm = con.prepareStatement("delete from joueur where jno=?;");
            stm.setInt(1, id);
            stm.executeUpdate();

        } catch (SQLException error) {
            System.out.println(error.getMessage());
        }
    }
}
