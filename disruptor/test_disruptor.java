import com.lmax.disruptor.*;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * 单生产者单消费者示例
 */
class SimpleTest {
    public static void main(String[] args) {
        Executor executor = Executors.newCachedThreadPool();
        LongEventFactory longEventFactory = new LongEventFactory();
        int bufferSize = 256;
        Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(longEventFactory, bufferSize, executor, ProducerType.SINGLE, new BlockingWaitStrategy());
        disruptor.handleEventsWith(new LongEventHandler());
        disruptor.start();

        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();
        for (int x = 0; x < 256; x++) {
            long sequence = ringBuffer.next();
            try {
                LongEvent event = ringBuffer.get(sequence);
                event.setValue(x);
            } finally {
                ringBuffer.publish(sequence);
            }
        }
    }

    static class LongEvent {
        private long value;

        public long getValue() {
            return value;
        }

        public void setValue(long value) {
            this.value = value;
        }
    }

    static class LongEventFactory implements EventFactory<LongEvent> {
        @Override
        public LongEvent newInstance() {
            return new LongEvent();
        }
    }

    static class LongEventHandler implements EventHandler<LongEvent> {
        @Override
        public void onEvent(LongEvent longEvent, long sequence, boolean endOfBatch) throws Exception {
            System.out.println("Event：" + longEvent.getValue() + ", sequence: " + sequence);
        }
    }
}

/*
javac test_disruptor.java -classpath ./disruptor-4.0.0.RC1.jar
test_disruptor.java:16: 错误: 不兼容的类型: Executor无法转换为ThreadFactory
Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(longEventFactory, bufferSize, executor, ProducerType.SINGLE, new BlockingWaitStrategy());
 */