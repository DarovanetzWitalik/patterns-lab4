package lab4.chain;

public abstract class SupportHandler {
    private SupportHandler next;

    public SupportHandler linkWith(SupportHandler next) {
        this.next = next;
        return next;
    }

    public boolean handle(int choice, StringBuilder log) {
        if (canHandle(choice)) {
            log.append(question()).append(System.lineSeparator());
            log.append(resolveMessage(choice)).append(System.lineSeparator());
            return true;
        }

        log.append(question()).append(System.lineSeparator());
        log.append("Користувач обрав: ").append(choice).append(" -> передача на наступний рівень підтримки.")
                .append(System.lineSeparator());

        if (next == null) {
            log.append("Жоден рівень підтримки не зміг обробити запит.").append(System.lineSeparator());
            return false;
        }
        return next.handle(choice, log);
    }

    protected abstract boolean canHandle(int choice);

    protected abstract String question();

    protected abstract String resolveMessage(int choice);
}
