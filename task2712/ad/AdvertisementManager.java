package com.javarush.task.task27.task2712.ad;


import com.javarush.task.task27.task2712.ConsoleHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class AdvertisementManager {

    final AdvertisementStorage storage = AdvertisementStorage.getInstance();
    int timeSeconds;

    public AdvertisementManager(int timeSeconds) {
        this.timeSeconds = timeSeconds;
    }

    public void processVideos() throws NoVideoAvailableException {
        List<Advertisement> list = Collections.synchronizedList(new ArrayList<>());
        storage.list().stream().filter((ad)-> ad.getHits() > 0).forEach((ad) -> list.add(ad));
        if (list.isEmpty()) {
            throw new NoVideoAvailableException();
        }
        List<Advertisement> result = solve(new ArrayList<>(), timeSeconds, storage.list().size());


        Collections.sort(result, ((Comparator<Advertisement>) (o1, o2) -> (int) (o2.getAmountPerOneDisplaying() - o1.getAmountPerOneDisplaying()))
                .thenComparing((o1, o2) -> (int)(o1.getAmountPerOneDisplaying()/o1.getDuration() - o2.getAmountPerOneDisplaying()/o2.getDuration())));
        for (Advertisement a: result){
            ConsoleHelper.writeMessage(String.format("%s is displaying... %d, %d", a.getName(), a.getAmountPerOneDisplaying(), a.getAmountPerOneDisplaying() * 1000/a.getDuration() ));
        }
    }

    private List<Advertisement> solve(List<Advertisement> advertisementList, long time, int i){

        if (time <= 0 || i == 0 )
            return advertisementList;  // если кончилось время или кандидаты на показ то дело сделано

        Advertisement ad = storage.list().get(--i); // взяли рекламу из списка

        List<Advertisement> l1 = new ArrayList<>(advertisementList);
//        System.out.println("trying " + ad.getName());
        l1.add(ad);  // сделали дубль списка для показа с добавленой взятой рекламой


        if (getTotalTime(l1) > time)
            return solve(advertisementList, time, i);   // если время показа такого списка превышает заданное,
                                                                                    // то пропускаем этот вариант

        else {
             return Stream.of(solve(advertisementList, time, i), solve(l1, time - ad.getDuration(), i)).max(Comparator.comparingLong(AdvertisementManager::getTotalAmount)).get();
//            List<Advertisement> r1 = solve(advertisementList, time, new Integer(i));  // считаем лист без взятой рекламы, остаток времени тот же
//            List<Advertisement> r2 = solve(l1, time - ad.getDuration(), new Integer(i));  //считаем лист со взятой рекламой, остаток времени уменьшилось.

//            return getTotalAmount(r1) > getTotalTime(r2) ? r1 : r2;  // сравниваем полную стоимость реклам в листе,
        }
    }



    static class VideosListComparator implements Comparator<List<Advertisement>>{
        @Override
        public int compare(List<Advertisement> o1, List<Advertisement> o2) {
            long sum1 = 0, sum2 = 0;
            for (Advertisement a: o1){
                sum1 += a.getAmountPerOneDisplaying();
            }for (Advertisement a: o2){
                sum2 += a.getAmountPerOneDisplaying();
            }
            if ((sum1 - sum2) != 0) {
                return (int)(sum1 - sum2);
            }
            sum1 = getTotalTime(o1) - getTotalTime(o2);
            if (sum1 != 0) return (int)sum1;
            return o2.size() - o1.size();

        }

    }
    private static int getTotalTime(List<Advertisement> l){
        int res = 0;
        for (Advertisement a: l){
            res += a.getDuration();
        }
        return res;
    }

    private static long getTotalAmount(List<Advertisement> l){
        long amount = 0;
        for (Advertisement a: l){
            amount += a.getAmountPerOneDisplaying();
        }
//        System.out.println(amount);
        return amount;
    }
}

