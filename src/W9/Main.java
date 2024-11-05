package W9;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        ArrayList<Member> members = FileOperator.getMembersFromFile("src/W9/clubread.txt");

        Member maxMilageMember = members.stream().max(Comparator.comparingDouble(Member::getMiles)).orElse(null);
        ArrayList<Member> seventyPercentMembers = members.stream()
                .filter(member -> member.getMiles() > maxMilageMember.getMiles() * 0.7
                        && ((member.getName() != maxMilageMember.getName())
                                && (member.getSurname() != maxMilageMember.getSurname())))
                .collect(Collectors.toCollection(ArrayList::new));

        FileOperator.writeWinnerToFile(maxMilageMember, "src/W9/clubWinners.txt");
        FileOperator.appendWinnersToFile(seventyPercentMembers, "src/W9/clubWinners.txt");
    }
}
