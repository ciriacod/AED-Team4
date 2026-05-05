package actividadEjercicio4;

class LevelNode<E> {
    int priorityLevelInList;
    Node<E> firstElement; // Cabeza de la sub-cola
    LevelNode<E> nextLevel;

    public LevelNode(int priority) {
        this.priorityLevelInList = priority;
        this.firstElement = null;
        this.nextLevel = null;
    }
}