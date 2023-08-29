package org.prog.cars;

public class Owner {

    public String name;
    public String lastName;

    public String taxId;

    @Override
    public boolean equals(Object o) {
        if (o instanceof Owner) {
            Owner owner = (Owner) o;
            return owner.name.equals(this.name) && owner.lastName.equals(this.lastName);
//                    && owner.taxId.equals(this.taxId);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return (name + lastName).hashCode();
    }


}
