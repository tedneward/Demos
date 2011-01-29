public class App
{
    public static void main(String[] args)
    {
        ISpeak speaker = new SpeakFrench();
        System.out.println(speaker.sayHello());
    }
}