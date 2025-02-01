package org.Application;
/**
 * A consumer that retrieves messages from the message queue and processes them.
 */
public class Consumer implements Runnable {
    private final MessageQueue messageQueue;
    private int successfulMessages = 0;
    private int errorMessages = 0;
    /**
     * Constructor to initialize the consumer.
     *
     * @param messageQueue The shared message queue.
     */
    public Consumer(MessageQueue messageQueue) {
        this.messageQueue = messageQueue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String message = messageQueue.consume();
                if (processMessage(message)) {
                    successfulMessages++;
                } else {
                    errorMessages++;
                }
                System.out.println("Consumed: " + message);
                Thread.sleep(150); // Simulate time taken to process a message
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    /**
     * Simulates message processing with a 10% chance of failure.
     *
     * @param message The message to process.
     * @return true if the message is processed successfully, false otherwise.
     */

    private boolean processMessage(String message) {
        // Simulate message processing with a 10% chance of failure
        return Math.random() > 0.1;
    }
    /**
     * Returns the total number of messages processed successfully.
     *
     * @return The number of successful messages.
     */
    public int getSuccessfulMessages() {
        return successfulMessages;
    }
    /**
     * Returns the total number of messages that failed to process.
     *
     * @return The number of error messages.
     */
    public int getErrorMessages() {
        return errorMessages;
    }
}
