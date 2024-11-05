package W9;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileOperator {
    public static ArrayList<Member> getMembersFromFile(String membersInputFile) throws IOException {
        BufferedReader bf = new BufferedReader(new FileReader(membersInputFile));

        ArrayList<Member> members = new ArrayList<Member>();
        String newLine;

        while ((newLine = bf.readLine()) != null) {
            String[] memberInfo = newLine.split(",");
            members.add(new Member(memberInfo[0], memberInfo[1], Double.parseDouble(memberInfo[2])));
        }

        bf.close();

        return members;
    }

    public static void writeWinnerToFile(Member member, String winnersOutputFileName) {
        try (FileWriter fw = new FileWriter(winnersOutputFileName)) {
            fw.write(member.getName() + " " + member.getSurname() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void appendWinnersToFile(ArrayList<Member> seventyPercentMembers, String winnersOutputFileName) {
        try (FileWriter fw = new FileWriter(winnersOutputFileName, true)) {
            seventyPercentMembers.stream().forEach(member -> {
                try {
                    fw.append(member.getName() + " " + member.getSurname() + "\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
