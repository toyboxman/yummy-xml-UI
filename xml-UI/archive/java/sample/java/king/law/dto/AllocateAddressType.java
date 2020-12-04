package king.law.dto;

import java.util.Arrays;

public enum AllocateAddressType {
    IpPool, MacPool, Both, None, Dhcp, Management;

    public static AllocateAddressType fromString(String input) {
        if (input != null) {
            for (AllocateAddressType state : AllocateAddressType.values()) {
                if (input.equalsIgnoreCase(state.name())) {
                    return state;
                }
            }
        }
        return None;
    }

    public static void main(String[] args) {
        System.out.println(AllocateAddressType.fromString("Dhcp").name());
        System.out.println(AllocateAddressType.fromString("Dhcp").toString());
        Arrays.stream(AllocateAddressType.values()).forEach(System.out::println);
    }
}
