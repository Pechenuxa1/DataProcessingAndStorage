public class Main {
  public static void main(String[] args) {
    int numOfWidgets = Integer.parseInt(args[0]);
    ProductionLine productionLine = new ProductionLine(numOfWidgets);
    productionLine.run();
  }
}