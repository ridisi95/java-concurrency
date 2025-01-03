package course.michael.pogrebinsky.section3.thread.cordination;

import java.math.BigInteger;

public class ComplexCalculation {
  public BigInteger calculateResult(BigInteger base1, BigInteger power1, BigInteger base2, BigInteger power2)
      throws InterruptedException {

    PowerCalculatingThread thread1 = new PowerCalculatingThread(base1, power1);
    PowerCalculatingThread thread2 = new PowerCalculatingThread(base2, power2);

    thread1.start();
    thread2.start();
    thread1.join();
    thread2.join();

    return thread1.getResult().add(thread2.getResult());
  }

  private static class PowerCalculatingThread extends Thread {
    private BigInteger result = BigInteger.ONE;
    private final BigInteger base;
    private final BigInteger power;

    public PowerCalculatingThread(BigInteger base, BigInteger power) {
      this.base = base;
      this.power = power;
    }

    @Override
    public void run() {
      for(BigInteger i = power; i.compareTo(BigInteger.ZERO) != 0; i = i.subtract(BigInteger.ONE)) {
        result = result.multiply(base);
      }
    }

    public BigInteger getResult() {
      return result;
    }
  }
}
