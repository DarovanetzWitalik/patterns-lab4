package lab4.chain;

public class TechnicalSupportHandler extends SupportHandler {
    @Override
    protected boolean canHandle(int choice) {
        return choice == 2;
    }

    @Override
    protected String question() {
        return "Рівень 2. Якщо у вас технічна проблема із сервісом, натисніть 2.";
    }

    @Override
    protected String resolveMessage(int choice) {
        return "Підключено технічну підтримку: діагностика помилок та відновлення роботи сервісу.";
    }
}
