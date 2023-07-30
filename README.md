```java
public static final EventBus BUS = new EventBus();
public static final Event EVENT = new CopyOnWriteEvent<Object>(); //or LogSizeArrayEvent<Object> for thread unsafe

public static void main(String[] args) {
    BUS.registerEventType(EVENT);
    BUS.subscribe(new Listener() {
        @Override
        public void listen(Object event) {
            System.out.println("listened");
        }
    })
    BUS.post(EVENT, new Object());
}

```