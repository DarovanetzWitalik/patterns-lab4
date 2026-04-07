package lab4.chain;

public class VipSupportHandler extends SupportHandler {
    @Override
    protected boolean canHandle(int choice) {
        return choice == 4;
    }

    @Override
    protected String question() {
        return "Рівень 4. Якщо ви VIP-клієнт і хочете персонального менеджера, натисніть 4.";
    }

    @Override
    protected String resolveMessage(int choice) {
        return "Підключено VIP-підтримку: персональний менеджер готовий допомогти.";
    }
}
