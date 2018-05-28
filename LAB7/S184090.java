package lab7;

public class S184090 {
    private static String username = "ser";
    private static String password = "ser";
    public static void main(String[] args) {
        boolean isFind;
        try {
            int i = Integer.valueOf(args[0]);
            if (i == 0) {
                isFind = true;
            } else if (i == 1) {
                isFind = false;
            } else {
                throw new Exception("");
			}
        } catch (Exception e) {
            System.out.println("wrong parameter");
            return;
        }
        if (isFind) {
            if (args.length < 2) {
                System.out.println("name of business not found");
			}
            (new SimpleBrowse()).browse(username, password, args[1]);
        } else {
            if (args.length < 3) {
                System.out.println("name of business/service not found");
			}
            (new SimplePublishPortable()).publish(username, password, args[1], args[2]);
        }
    }
}