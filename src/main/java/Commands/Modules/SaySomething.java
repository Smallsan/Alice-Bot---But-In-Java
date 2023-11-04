package Commands.Modules;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class SaySomething {
    private static final Random random = new Random();
    private static final List<String> welcomeMessage = Arrays.asList(
            "I really do love you, i love you so much, i want to eat you so we can be together forever.",
            "I would do anything to keep you all to myself, even if it means getting rid of everyone else.",
            "I love you so much that I can't bear the thought of anyone else being close to you.",
            "I will never let you go, no matter what it takes.",
            "If you ever leave me, I will make sure that you regret it for the rest of your life.",
            "I would rather die than let anyone else have you.",
            "I know everything about you, and I will stop at nothing to make you mine forever.",
            "I would kill for you, and I would die for you.",
            "I will always be watching you, even when you don't know it.",
            "You belong to me, and only me.",
            "I will always be by your side, even if you try to push me away."
    );

    public static String SayIt() {
        return welcomeMessage.get(random.nextInt(welcomeMessage.size()));
    }
}
