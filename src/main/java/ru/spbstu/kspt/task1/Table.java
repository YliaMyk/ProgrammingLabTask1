package main.java.ru.spbstu.kspt.task1;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

/**
 * Main class
 */
public class Table {
    TreeMap<Double, Double> pairMap = new TreeMap<Double, Double>(); // добавить пару
    private ArrayList<Object> difference;

    public void addValue(double x, double y) {
        pairMap.put(x, y);
    }

    public Double funValue(double x) { // вывести для х у
        return pairMap.get(x);
    }

    public void dellValue(double x) { // удалить пару
        pairMap.remove(x);
    }

    public String showTable(){
        StringBuilder res = new StringBuilder();
        for (Map.Entry e:pairMap.entrySet()) {
            res.append(e.getKey()).append(" ").append(e.getValue()).append("\n");
        }
        return res.toString();
    }

    public String findClosestPair(double x0) {// найти ближайшее значени для х0
        int low = 0;
        int up = pairMap.size();
        int comp = -1;
        double dif = (double) pairMap.values().toArray()[pairMap.size()-1];
        int res = -1;
        while (low != up){
            comp = (low + up) / 2;
            if(dif > (x0 - (double) pairMap.keySet().toArray()[comp])){
                dif = x0 - (double) pairMap.keySet().toArray()[comp];
                res = comp;
            }
            if(dif < 0.01)
                return pairMap.keySet().toArray()[comp] + " " + pairMap.values().toArray()[comp];
            else if (Double.compare((double)pairMap.keySet().toArray()[comp], x0) < 0)
                up = comp;
            else
                low = ++comp;
        }
        return pairMap.keySet().toArray()[res] + " " + pairMap.values().toArray()[res];
    }

    private Double[] diff() {
        int n = pairMap.size();
        Double[] diff = new Double[n];
        for (int i = 0; i < n; i++)
            diff[i] = (Double) pairMap.values().toArray()[i];
        for (int j = 1; j < n; j++)
            for (int i = n - 1; i > j - 1; i--)
                    diff[i] = (diff[i] - diff[i - 1])
                            /((Double)pairMap.keySet().toArray()[i] - (Double)pairMap.keySet().toArray()[i - j]);
        return diff;
    }

    public Double calculateNewtonPolynom(double x0) {
        Double[] a = diff();
        int n = a.length - 1;
        Double temp = a[n];
        for (int i = n - 1; i > -1;  i--)
            temp = temp * (x0 - (Double) pairMap.keySet().toArray()[i]) + a[i];
        return temp;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for(Map.Entry e : pairMap.entrySet()){
            res.append("x: ").append(e.getKey()).append(" y: ").append(e.getValue()).append("\n");
        }
        return res.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Table table = (Table) o;
        return Objects.equals(pairMap, table.pairMap) &&
                Objects.equals(difference, table.difference);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pairMap, difference);
    }
}