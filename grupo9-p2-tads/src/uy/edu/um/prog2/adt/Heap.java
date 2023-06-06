package uy.edu.um.prog2.adt;


public class Heap<T extends Comparable<T>> implements MyHeap<T> {
    private ArrayList<T> heap;

    public Heap() {
        heap = new ArrayList<>();
    }

    @Override
    public void add(T element) {
        heap.add(element);
        siftUp(heap.size() - 1);
    }

    @Override
    public T getAndRemove() {
        if (heap.isEmpty()) {
            return null;
        }

        T root = heap.get(0);
        T lastElement = heap.remove(heap.size() - 1);

        if (!heap.isEmpty()) {
            heap.set(0, lastElement);
            siftDown(0);
        }

        return root;
    }


    @Override
    public int getSize() {
        return heap.size();
    }

    @Override
    public void printHeap() {
        for (int i = 0; i < heap.size(); i++) {
            System.out.print(heap.get(i) + " ");
        }
        System.out.println();
    }

    private void siftUp(int index) {
        while (index > 0) {
            int parentIndex = (index - 1) / 2;
            if (heap.get(parentIndex).compareTo(heap.get(index)) < 0) {
                swap(parentIndex, index);
                index = parentIndex;
            } else {
                break;
            }
        }
    }

    private void siftDown(int index) {
        int size = heap.size();
        while (index < size) {
            int leftChildIndex = 2 * index + 1;
            int rightChildIndex = 2 * index + 2;
            int largest = index;

            if (leftChildIndex < size && heap.get(leftChildIndex).compareTo(heap.get(largest)) > 0) {
                largest = leftChildIndex;
            }
            if (rightChildIndex < size && heap.get(rightChildIndex).compareTo(heap.get(largest)) > 0) {
                largest = rightChildIndex;
            }

            if (largest != index) {
                swap(largest, index);
                index = largest;
            } else {
                break;
            }
        }
    }

    private void swap(int i, int j) {
        T temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }
}