
package Ejercicio3;

/*
 ***********************************************************************************************
 *************  ----------------------   Ejercicio N°3   ------------------  *******************
 ***********************************************************************************************
 */

//Arbol con operaciones genericas
public class LinkedBST_Ejercicio3<T extends Comparable<T>> {
    // 03a. Área del BST (Hojas * Altura) [cite: 117, 118]
    // Ejercicio 3: Área (Hojas * Altura Total)
    public int areaBST() {
        if (root == null) return 0;

        // Calculamos la altura total llamando al método height desde la raíz
        int totalHeight = height(root.data);

        // Contamos las hojas de forma iterativa con la cola
        MyQueue<Node<E>> queue = new MyQueue<>();
        queue.enqueue(root);
        int leavesCount = 0;

        while (!queue.isEmpty()) {
            Node<E> current = queue.poll();

            // Un nodo es hoja si ambos hijos son nulos
            if (current.left == null && current.right == null) {
                leavesCount++;
            }

            if (current.left != null) queue.enqueue(current.left);
            if (current.right != null) queue.enqueue(current.right);
        }

        return leavesCount * totalHeight;
    }
    
    public void drawBST() {
        System.out.println(this.toString());
    }
}
