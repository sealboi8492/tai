public class uczenModel{

    boolean walidujLogowanie(String user, String pass) throws InterruptedException {
        Thread.sleep(10000);
        if (!user.equals("admin") || !pass.equals("haslo123")) {
            return false;
        }
        return true;
    }
    }
