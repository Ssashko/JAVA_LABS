package lab3;

import java.util.Comparator;
import java.util.List;
/**
 * It's a class that describes a comparator of Motherboard.
 * @author Oleksandr Marianchuk
 * */
public class MotherboardComparator implements Comparator<Motherboard> {

    private static class ComparablePair<T extends Comparable<T>> {
        public T first;
        public T second;

        ComparablePair(T first, T second){
            this.first = first;
            this.second = second;
        }
        int compare() {
            return first.compareTo(second);
        }

    }
    private int compareRec(List<ComparablePair<?>> params, int head)
    {
        if (head == params.size())
            return 0;
        int comp = params.get(head).compare();
        if(comp == 0)
            compareRec(params,head+1);
        return comp;
    }


    @Override
    public int compare(Motherboard o1, Motherboard o2) {
        return compareRec(List.of(
                new ComparablePair<>(o1.getCpu(), o2.getCpu()),
                new ComparablePair<>(MotherboardService.getCapacityOfRams(o1),MotherboardService.getCapacityOfRams(o2)),
                new ComparablePair<>(MotherboardService.getFrequencyOfRams(o1),
                        MotherboardService.getFrequencyOfRams(o2)),
                new ComparablePair<>(MotherboardService.getSummaryCapacityOfDisks(o1),
                        MotherboardService.getSummaryCapacityOfDisks(o2)),
                new ComparablePair<>(MotherboardService.getAverageRandomSpeedOfRead(o1),
                        MotherboardService.getAverageRandomSpeedOfRead(o2)),
                new ComparablePair<>(MotherboardService.getAverageLinearSpeedOfRead(o1),
                        MotherboardService.getAverageLinearSpeedOfRead(o2)),
                new ComparablePair<>(MotherboardService.getAverageRandomSpeedOfWrite(o1),
                        MotherboardService.getAverageRandomSpeedOfWrite(o2)),
                new ComparablePair<>(MotherboardService.getAverageLinearSpeedOfWrite(o1),
                        MotherboardService.getAverageLinearSpeedOfWrite(o2)),
                new ComparablePair<>(o1.getVendor(), o2.getVendor()),
                new ComparablePair<>(o1.getPid(), o2.getPid())
        ),0);
    }

}
