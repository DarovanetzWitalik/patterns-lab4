package lab4.chain;

import java.util.List;

public class SupportMenu {
    private final SupportHandler firstHandler;

    public SupportMenu() {
        firstHandler = new BillingSupportHandler();
        firstHandler
                .linkWith(new TechnicalSupportHandler())
                .linkWith(new AccountSupportHandler())
                .linkWith(new VipSupportHandler());
    }

    public String runScenario(List<Integer> choices) {
        StringBuilder log = new StringBuilder();
        log.append("=== Ланцюжок відповідальності: система підтримки ===").append(System.lineSeparator());
        int attempt = 1;
        for (int choice : choices) {
            log.append("Спроба ").append(attempt++).append(":").append(System.lineSeparator());
            boolean handled = firstHandler.handle(choice, log);
            if (handled) {
                log.append("Меню завершено успішно.").append(System.lineSeparator());
                return log.toString();
            }
            log.append("Потрібно повторити меню знову.").append(System.lineSeparator()).append(System.lineSeparator());
        }
        log.append("Користувач не обрав коректний варіант навіть після повторів.").append(System.lineSeparator());
        return log.toString();
    }
}
