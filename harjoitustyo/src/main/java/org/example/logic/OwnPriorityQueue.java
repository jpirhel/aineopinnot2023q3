package org.example.logic;

import org.example.data.AirportDistance;

/**
 * Own implementation of an array-based priority queue. Uses [1] as an inspiration.
 * [1]: https://www.geeksforgeeks.org/binary-heap/
 */
public class OwnPriorityQueue {
    private final AirportDistance[] arr;
    private int lastIndex;

    /**
     * Constructor for priority queue object.
     *
     * @param size The size of the queue.
     */
    public OwnPriorityQueue(int size) {
        this.arr = new AirportDistance[size];
        this.lastIndex = -1; // empty queue
    }

    /**
     * Adds an airport distance to the queue.
     *
     * @param airportDistance The object to be added
     */
    public void add(AirportDistance airportDistance) {
        if (airportDistance == null) {
            return;
        }

        lastIndex += 1;

        // special case for empty priority queue

        if (isEmpty()) {
            arr[0] = airportDistance;
            return;
        }

        arr[lastIndex] = airportDistance;


        int i = lastIndex;

        while (i != 0) {
            int parentIndex = getParentIndex(i);

            AirportDistance curr = arr[i];
            AirportDistance parent = arr[getParentIndex(i)];

            if (curr.getDistance() > parent.getDistance()) {
                break;
            }

            // move current airport distance up the binary tree
            swap(i, parentIndex);

            i = parentIndex;
        }

        int parentIndex = getParentIndex(lastIndex);

        // check that the tree satisfies the min-heap property
        heapify(parentIndex);
    }

    /**
     * Checks if the queue is currently empty.
     *
     * @return True if the queue is empty
     */
    public Boolean isEmpty() {
        return lastIndex == -1;
    }

    /**
     * Returns and removes the currently smallest distance in the queue.
     *
     * @return The airport distance object
     */
    public AirportDistance poll() {
        if (isEmpty()) {
            return null;
        }

        AirportDistance smallestAirportDistance = arr[0];

        // special case for array of size 1

        if (lastIndex == 0) {
            lastIndex -= 1;
            arr[0] = null;
            return smallestAirportDistance;
        }

        // move last to root
        swap(lastIndex, 0);

        // clear last
        arr[lastIndex] = null;

        // reduce array size (last index)
        lastIndex -= 1;

        // check that tree is still correct (satisfies the min-heap property)
        heapify(0);

        return smallestAirportDistance;
    }

    /**
     * Heapifies the current subtree. Ensures that the min-heap property is satisfied.
     * @param i The index to be heapified
     */
    private void heapify(int i) {
        int left = getLeftChildIndex(i);
        int right = getRightChildIndex(i);

        int smallestDistance = arr[i].getDistance();

        int smallestIndex = i;

        // compare to left child node

        if (left <= lastIndex) {
            int distance = arr[left].getDistance();
            if (distance < smallestDistance) {
                smallestIndex = left;
                // update the smallest distance if it has changed
                smallestDistance = distance;
            }
        }

        // compare to right child node

        if (right <= lastIndex) {
            int distance = arr[right].getDistance();
            if (distance < smallestDistance) {
                smallestIndex = right;
            }
        }

        // check if smallestIndex changed

        if (smallestIndex != i) {
            swap(i, smallestIndex);
            heapify(smallestIndex);
        }
    }

    /**
     * Swaps to positions in an array.
     *
     * @param from The "from" index to be swapped
     * @param to The "to" index to be swapped
     */
    private void swap(int from, int to) {
        AirportDistance temp = arr[from];
        arr[from] = arr[to];
        arr[to] = temp;
    }

    /**
     * Parent index of the current node.
     *
     * @param i The current node index
     * @return The index of the parent node
     */
    private int getParentIndex(int i) {
        return (i - 1) / 2;
    }

    /**
     * Left child index of the current node.
     *
     * @param i The current node (parent)
     * @return The index of the left child
     */
    private int getLeftChildIndex(int i) {
        return (2 * i) + 1;
    }

    /**
     * Right child index of the current node.
     *
     * @param i The current node (parent)
     * @return The index of the right child
     */
    private int getRightChildIndex(int i) {
        return (2 * i) + 2;
    }
}
