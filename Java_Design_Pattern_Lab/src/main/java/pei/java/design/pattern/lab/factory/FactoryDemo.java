package pei.java.design.pattern.lab.factory;

import lombok.Getter;
import static com.github.peiatgithub.java.utils.Constants.*;

/**
 * 
 * @author pei
 *
 */
public class FactoryDemo {

    public static void main(String[] args) {
        NameFactory nameFactory = new NameFactory();

        FullName fullName1 = nameFactory.createName("Michael Jordan");
        FullName fullName2 = nameFactory.createName("Jordan, Michael");
        //
        System.out.format("Class: %s; FirstName: %s; LastName: %s%n"
                , fullName1.getClass().getName(), fullName1.getFirstName(), fullName1.getLastName());
        System.out.format("Class: %s; FirstName: %s; LastName: %s%n"
                , fullName2.getClass().getName(), fullName2.getFirstName(), fullName2.getLastName());
    }
}

class NameFactory {
    
    public FullName createName(String nameString) {
        if (nameString.contains(COMMA)) {
            return new LFName(nameString);
        } else {
            return new FLName(nameString);
        }
    }
    
}

/**
 * Allow user to enter name either as "firstname lastname" or as "lastname, firstname".  
 * Decide the name order by whether there is a comma between the last and first name.
 */
@Getter
class FullName {
    protected String lastName; 
    protected String firstName;
}

class FLName extends FullName {
    public FLName(String s) {
        String[] flNames = s.split(SPACE);
        if(flNames.length == 1) {// no space
            firstName = EMPTY;
            lastName = flNames[0].trim();
        }else if(flNames.length == 2) {
            firstName = flNames[0].trim();
            lastName = flNames[1].trim();
        }else {
            throw new RuntimeException("Parsing input name error: " + s);
        }
    }
}
class LFName extends FullName {
    public LFName(String s) {
        String[] flNames = s.split(COMMA);
        if(flNames.length == 1) {// no comma
            throw new RuntimeException("Input name does not contain comma: " + s);
        }else if(flNames.length == 2) {
            lastName = flNames[0].trim();
            firstName = flNames[1].trim();
        }else {
            throw new RuntimeException("Parsing input name error: " + s);
        }
    }
}