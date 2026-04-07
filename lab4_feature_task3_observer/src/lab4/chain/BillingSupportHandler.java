package lab4.chain;

public class BillingSupportHandler extends SupportHandler {
    @Override
    protected boolean canHandle(int choice) {
        return choice == 1;
    }

    @Override
    protected String question() {
        return "Рівень 1. Якщо питання стосується оплати або тарифу, натисніть 1.";
    }

    @Override
    protected String resolveMessage(int choice) {
        return "Підключено фінансовий відділ: допомога з оплатою, тарифами та рахунками.";
    }
}
