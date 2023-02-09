import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
/**
 * Defines a library of selection methods on Collections.
 *
 * @author Aidan Kiser (ark0053@auburn.edu)
 * @version 9 February 2023
 */
public final class Selector {

/**
 * Can't instantiate this class.
 *
 * D O   N O T   C H A N G E   T H I S   C O N S T R U C T O R
 *
 */
    private Selector() { }


    /**
     * Returns the minimum value in the Collection coll as defined by the
     * Comparator comp. If either coll or comp is null, this method throws an
     * IllegalArgumentException. If coll is empty, this method throws a
     * NoSuchElementException. This method will not change coll in any way.
     *
     * @param coll    the Collection from which the minimum is selected
     * @param comp    the Comparator that defines the total order on T
     * @return        the minimum value in coll
     * @throws        IllegalArgumentException as per above
     * @throws        NoSuchElementException as per above
     */
    public static <T> T min(Collection<T> coll, Comparator<T> comp) {
        if ((coll == null) || (comp == null)) {
            throw new IllegalArgumentException("Bruh this man null.");
        }
        if (coll.isEmpty()) {
            throw new NoSuchElementException("Bruh this man empty.");
        }

        Iterator<T> iterator = coll.iterator();
        T min = iterator.next(); // Creates iterator and makes it min.

        // Loops through coll and compares the values of every element in coll.
        for (T value : coll) { 
            if (comp.compare(value, min) < 0) {
                min = value;
            }
        }

        return min;
    }


    /**
     * Selects the maximum value in the Collection coll as defined by the
     * Comparator comp. If either coll or comp is null, this method throws an
     * IllegalArgumentException. If coll is empty, this method throws a
     * NoSuchElementException. This method will not change coll in any way.
     *
     * @param coll    the Collection from which the maximum is selected
     * @param comp    the Comparator that defines the total order on T
     * @return        the maximum value in coll
     * @throws        IllegalArgumentException as per above
     * @throws        NoSuchElementException as per above
     */
    public static <T> T max(Collection<T> coll, Comparator<T> comp) {
        if ((coll == null) || (comp == null)) {
            throw new IllegalArgumentException("Bruh this man null.");
        }
        if (coll.isEmpty()) {
            throw new NoSuchElementException("Bruh this man empty.");
        }

        Iterator<T> iterator = coll.iterator();
        T max = iterator.next(); // Creates iterator and makes it min.

        // Loops through coll and compares the values of every element in coll.
        for (T value : coll) { 
            if (comp.compare(value, max) > 0) {
                max = value;
            }
        }

        return max;
    }


    /**
     * Selects the kth minimum value from the Collection coll as defined by the
     * Comparator comp. If either coll or comp is null, this method throws an
     * IllegalArgumentException. If coll is empty or if there is no kth minimum
     * value, this method throws a NoSuchElementException. This method will not
     * change coll in any way.
     *
     * @param coll    the Collection from which the kth minimum is selected
     * @param k       the k-selection value
     * @param comp    the Comparator that defines the total order on T
     * @return        the kth minimum value in coll
     * @throws        IllegalArgumentException as per above
     * @throws        NoSuchElementException as per above
     */
    public static <T> T kmin(Collection<T> coll, int k, Comparator<T> comp) {
        if ((coll == null) || (comp == null)) {
            throw new IllegalArgumentException("Bruh this man null.");
        }
        if (coll.isEmpty()) {
            throw new NoSuchElementException("Bruh this man empty.");
        }
        if (k < 1 || k > coll.size()) {
            throw new NoSuchElementException("Not a valid k bruh.");
        }

        int undistinctValues = 0;
        ArrayList<T> collDuplicate = new ArrayList<T>(coll);
        java.util.Collections.sort(collDuplicate, comp);
        Iterator<T> iterator = collDuplicate.iterator();
        iterator.next();

        for (T value : collDuplicate) {
            if (iterator.hasNext()) {
                if (comp.compare(value, iterator.next()) == 0) {
                    undistinctValues++;
                }
            }
        }

        if (k > coll.size() - undistinctValues) {
            throw new NoSuchElementException("Not a valid k bruh.");
        }

        for (int i = 0; i < k - 1; i++) {
            if (collDuplicate.get(i) == collDuplicate.get(i + 1)) {
                k++; // Skips duplicates.
            }
        }

        T kmin = collDuplicate.get(k - 1);

        return kmin;
    }


    /**
     * Selects the kth maximum value from the Collection coll as defined by the
     * Comparator comp. If either coll or comp is null, this method throws an
     * IllegalArgumentException. If coll is empty or if there is no kth maximum
     * value, this method throws a NoSuchElementException. This method will not
     * change coll in any way.
     *
     * @param coll    the Collection from which the kth maximum is selected
     * @param k       the k-selection value
     * @param comp    the Comparator that defines the total order on T
     * @return        the kth maximum value in coll
     * @throws        IllegalArgumentException as per above
     * @throws        NoSuchElementException as per above
     */
    public static <T> T kmax(Collection<T> coll, int k, Comparator<T> comp) {
        if ((coll == null) || (comp == null)) {
            throw new IllegalArgumentException("Bruh this man null.");
        }
        if (coll.isEmpty()) {
            throw new NoSuchElementException("Bruh this man empty.");
        }
        if (k < 1 || k > coll.size()) {
            throw new NoSuchElementException("Not a valid k bruh.");
        }

        int undistinctValues = 0;
        ArrayList<T> collDuplicate = new ArrayList<T>(coll);
        java.util.Collections.sort(collDuplicate, comp);
        Iterator<T> iterator = collDuplicate.iterator();
        iterator.next();

        for (T value : collDuplicate) {
            if (iterator.hasNext()) {
                if (comp.compare(value, iterator.next()) == 0) {
                    undistinctValues++;
                }
            }
        }

        if (k > coll.size() - undistinctValues) {
            throw new NoSuchElementException("Not a valid k bruh.");
        }

        for (int i = coll.size() - 1; i > coll.size() - k; i--) {
            if (collDuplicate.get(i) == collDuplicate.get(i - 1)) {
                k++; // Skips duplicates.
            }
        }

        T kmax = collDuplicate.get(coll.size() - k);

        return kmax;
    }


    /**
     * Returns a new Collection containing all the values in the Collection coll
     * that are greater than or equal to low and less than or equal to high, as
     * defined by the Comparator comp. The returned collection must contain only
     * these values and no others. The values low and high themselves do not have
     * to be in coll. Any duplicate values that are in coll must also be in the
     * returned Collection. If no values in coll fall into the specified range or
     * if coll is empty, this method throws a NoSuchElementException. If either
     * coll or comp is null, this method throws an IllegalArgumentException. This
     * method will not change coll in any way.
     *
     * @param coll    the Collection from which the range values are selected
     * @param low     the lower bound of the range
     * @param high    the upper bound of the range
     * @param comp    the Comparator that defines the total order on T
     * @return        a Collection of values between low and high
     * @throws        IllegalArgumentException as per above
     * @throws        NoSuchElementException as per above
     */
    public static <T> Collection<T> range(Collection<T> coll, T low, T high,
        Comparator<T> comp) {
            if ((coll == null) || (comp == null)) {
                throw new IllegalArgumentException("Bruh this man null.");
            }
            if (coll.isEmpty()) {
                throw new NoSuchElementException("Bruh this man empty.");
            }

            ArrayList<T> range = new ArrayList<T>();

            for (T value : coll) {
                if (comp.compare(value, low) >= 0 && comp.compare(value, high) <= 0) {
                    range.add(value);
                }
            }

            if (range.isEmpty()) {
                throw new NoSuchElementException("There ain't no values mane.");
            }

            return range;
        }

    /**
     * Returns the smallest value in the Collection coll that is greater than
     * or equal to key, as defined by the Comparator comp. The value of key
     * does not have to be in coll. If coll or comp is null, this method throws
     * an IllegalArgumentException. If coll is empty or if there is no
     * qualifying value, this method throws a NoSuchElementException. This
     * method will not change coll in any way.
     *
     * @param coll    the Collection from which the ceiling value is selected
     * @param key     the reference value
     * @param comp    the Comparator that defines the total order on T
     * @return        the ceiling value of key in coll
     * @throws        IllegalArgumentException as per above
     * @throws        NoSuchElementException as per above
     */
    public static <T> T ceiling(Collection<T> coll, T key, Comparator<T> comp) {
        if ((coll == null) || (comp == null)) {
            throw new IllegalArgumentException("Bruh this man null.");
        }
        if (coll.isEmpty()) {
            throw new NoSuchElementException("Bruh this man empty.");
        }

        T ceiling = Selector.max(coll, comp);
        boolean ceilingFound = false;

        for (T value : coll) {
            if (comp.compare(value, key) >= 0 && comp.compare(value, ceiling) <= 0) {
                ceiling = value;
                ceilingFound = true;
            }
        }

        if (!ceilingFound) {
            throw new NoSuchElementException("There ain't no qualifying values bruh.");
        }

        return ceiling;
    }


    /**
     * Returns the largest value in the Collection coll that is less than
     * or equal to key, as defined by the Comparator comp. The value of key
     * does not have to be in coll. If coll or comp is null, this method throws
     * an IllegalArgumentException. If coll is empty or if there is no
     * qualifying value, this method throws a NoSuchElementException. This
     * method will not change coll in any way.
     *
     * @param coll    the Collection from which the floor value is selected
     * @param key     the reference value
     * @param comp    the Comparator that defines the total order on T
     * @return        the floor value of key in coll
     * @throws        IllegalArgumentException as per above
     * @throws        NoSuchElementException as per above
     */
    public static <T> T floor(Collection<T> coll, T key, Comparator<T> comp) {
        if ((coll == null) || (comp == null)) {
            throw new IllegalArgumentException("Bruh this man null.");
        }
        if (coll.isEmpty()) {
            throw new NoSuchElementException("Bruh this man empty.");
        }

        T floor = Selector.min(coll, comp);
        boolean floorFound = false;

        for (T value : coll) {
            if (comp.compare(value, key) <= 0 && comp.compare(value, floor) >= 0) {
                floor = value;
                floorFound = true;
            }
        }

        if (!floorFound) {
            throw new NoSuchElementException("There ain't no qualifying values bruh.");
        }

        return floor;
    }

}
