package W8.Blackjack;

import java.util.Scanner;

public class Blackjack {
    public static void main(String[] args) {
        Deck deck = new Deck();
        Hand playerHand = new Hand();
        Hand dealerHand = new Hand();
        Scanner scanner = new Scanner(System.in);

        int count = 0;

        System.out.println("\n       Blackjack       \nWelcome to the new game\n");
        int roundCount = 1;

        while (count > -6) {
            System.out.println(deck.getSize());

            try {
                playerHand.addCard(deck.drawCard());
                playerHand.addCard(deck.drawCard());
                dealerHand.addCard(deck.drawCard());
                Card hiddenCard = deck.drawCard();
                hiddenCard.hide();
                dealerHand.addCard(hiddenCard);
            } catch (Exception e) {
                System.out.println("The deck is empty! The game is over.");
                System.exit(0);
            }

            System.out.println("Round " + roundCount + ", count: " + count);
            System.out.println("Dealer's " + dealerHand + "\n");
            System.out.println("Your " + playerHand + "\n");

            // Player's turn
            boolean playerBusted = false;

            if (playerHand.calculateValue() == 21) {
                System.out.println("You win! \n");
                playerBusted = true;
                count++;
            }

            if (!playerBusted) {
                while (true && playerHand.amountOfCards() < 6) {
                    System.out.print("Do you want to (h)it or (s)tand? ");
                    String choice = scanner.nextLine().toLowerCase();
                    System.out.println();

                    if (choice.equals("h")) {
                        try {
                            playerHand.addCard(deck.drawCard());
                        } catch (Exception e) {
                            System.out.println("The deck is empty! The game is over.");
                            System.exit(0);
                        }
                        System.out.println("--------------------------------------------------\n");
                        System.out.println("Your " + playerHand + "\n");
                        if (playerHand.calculateValue() > 21) {
                            System.out.println("You busted! Dealer wins.\n");
                            playerBusted = true;
                            count--;
                            break;
                        } else if (playerHand.calculateValue() == 21) {
                            System.out.println("You win! \n");
                            playerBusted = true;
                            count++;
                            break;
                        }
                    } else if (choice.equals("s")) {
                        break;
                    } else {
                        System.out.println("Invalid choice! Please enter 'h' or 's'.");
                    }
                }

                if (!playerBusted) {
                    dealerHand.getCards().get(1).reveal();
                    System.out.println("--------------------------------------------------\n");
                    System.out.println("Dealer's " + dealerHand + "\n");
                    if (dealerHand.calculateValue() == 21) {
                        System.out.println("Dealer wins! \n");
                        playerBusted = true;
                        count--;
                    }
                }

                // Dealer's turn
                if (!playerBusted) {
                    while (dealerHand.calculateValue() < 17 && dealerHand.amountOfCards() < 6) {
                        try {
                            dealerHand.addCard(deck.drawCard());
                        } catch (Exception e) {
                            System.out.println("The deck is empty! The game is over.");
                            System.exit(0);
                        }
                        System.out.println("--------------------------------------------------\n");
                        System.out.println("Dealer's " + dealerHand + "\n");
                    }

                    // Determine winner
                    int playerTotal = playerHand.calculateValue();
                    int dealerTotal = dealerHand.calculateValue();

                    if (dealerTotal > 21) {
                        System.out.println("Dealer busted! You win.\n");
                        count++;
                    } else if (playerTotal > dealerTotal) {
                        System.out.println("You win!\n");
                        count++;
                    } else if (playerTotal < dealerTotal) {
                        System.out.println("Dealer wins!\n");
                        count--;
                    } else {
                        System.out.println("It's a tie!\n");
                    }
                }
            }

            playerHand.cleanHand();
            dealerHand.cleanHand();

            roundCount++;
        }

        scanner.close();
    }
}
