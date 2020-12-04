package king.law.antlr;

import org.stringtemplate.v4.ST;

public class BuildFile {
    public static void main(String[] args) {
        ST st = new ST("<b>$u.id$</b>: $u.name$", '$', '$');
        st.add("u", new User(999, "parrt"));
        String result = st.render(); // "<b>999</b>: parrt"
        System.out.println(result);
    }

    public static class User {
        public int id; // template can directly access via u.id
        private String name; // template can't access this
        public User(int id, String name) { this.id = id; this.name = name; }
        public boolean isManager() { return true; } // u.manager
        public boolean hasParkingSpot() { return true; } // u.parkingSpot
        public String getName() { return name; } // u.name
        public String toString() { return id+":"+name; } // u
    }
}
