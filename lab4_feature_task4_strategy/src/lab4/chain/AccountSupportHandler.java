package lab4.chain;

public class AccountSupportHandler extends SupportHandler {
    @Override
    protected boolean canHandle(int choice) {
        return choice == 3;
    }

    @Override
    protected String question() {
        return "Рівень 3. Якщо потрібно відновити доступ до акаунта, натисніть 3.";
    }

    @Override
    protected String resolveMessage(int choice) {
        return "Підключено підтримку акаунтів: відновлення пароля та верифікація користувача.";
    }
}
