# Lab 4 — Behavioral Patterns

Проєкт містить реалізацію п'яти поведінкових шаблонів:
- Chain of Responsibility
- Mediator
- Observer
- Strategy
- Memento

## Запуск

### Linux / macOS
```bash
./run.sh
```

### Windows
```bat
run.bat
```

Або вручну:
```bash
javac -encoding UTF-8 -d out $(find src -name "*.java")
java -Dfile.encoding=UTF-8 -cp out lab4.app.Main
```

## Що ще потрібно для здачі
За умовою лабораторної потрібно здати 3 посилання:
1. окремо для завдань 1, 2, 5;
2. окремий PR для завдання 3 (Observer);
3. окремий PR для завдання 4 (Strategy).

У цьому пакеті додані шаблони описів для PR:
- `PR_TASK3_OBSERVER.md`
- `PR_TASK4_STRATEGY.md`
