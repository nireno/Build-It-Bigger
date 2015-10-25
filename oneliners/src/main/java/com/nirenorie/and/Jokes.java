package com.nirenorie.and;

public class Jokes {
    public static String getJoke(int i){
        return jokes[i];
    }

    public static String getRandomJoke(){
        return getJoke((int) (Math.random() * jokes.length));
    }

    private static String[] jokes = {
            "You only lose when you decide to ...",
            "Confusius say: \"He who drop watch in toilet, have shitty time.\"",
            "Talk is cheap until you hire a lawyer.",
            "Never hit a man with glasses. Hit him with something bigger and heavier.",
            "I often say to myself \"I can't believe that cloning machine worked\".",
            "When asked why he went vegan, the zombie responded \"it was a no-brainer\"",
            "They'll kill me if they know i'm using their kitchen utensils, but it's a whisk i'm willing to take.",
            "Turning vegan is a big missed steak",
            "Orion's Belt is just a big waist of space.",
            "I had two jokes about prime factors, but they were one and the same."
    };
}
