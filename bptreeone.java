import java.util.Scanner;

import javax.lang.model.type.NullType;

class operations {
    bpnode head;
    Scanner input = new Scanner(System.in);

    class bpnode {
        int[] data;
        int i = 0;
        bpnode[] childpointer;
        bpnode[] parentpointer;
        bpnode[] next;

        bpnode(int order) {
            this.data = new int[order];
            this.childpointer = new bpnode[order + 1];
            this.parentpointer = new bpnode[order + 1];
            this.next = new bpnode[1];
        }
    }

    void insertdatainaninternalnode(bpnode z, bpnode y, int order, int key) {
        if (z.data[order - 1] != 0) {
            if (z.parentpointer[0] == null) {
                z.data[order] = key;
                sort(z, order);
                bpnode x = new bpnode(order);
                bpnode xx = new bpnode(order);
                int k = key;
                if (k % 2 == 0) {
                    xx.data[0] = z.data[order / 2];
                    for (int i = 0; i <= order; i++) {
                        x.data[i] = z.data[1 + (i + (order) / 2)];
                    }
                } else {
                    xx.data[0] = z.data[(1 + order / 2)];
                    for (int i = 0; i <= order; i++) {
                        x.data[i] = z.data[1 + (i + ((1 + order) / 2))];
                    }
                }
                xx.childpointer[0] = z;
                xx.childpointer[1] = x;
            } else {
                z.data[order] = key;
                sort(z, order);
                bpnode x = new bpnode(order);
                bpnode xx = new bpnode(order);
                xx = z.parentpointer[0];
                int k = key;
                if (k % 2 == 0) {
                    if (xx.data[order - 1] != 0) {
                        xx.data[order] = z.data[(1 + order / 2)];
                        sort(xx, order);
                        for (int i = 0; i <= order; i++) {
                            x.data[i] = z.data[2 + (i + (order) / 2)];
                        }
                    } else {
                        insertdatainaninternalnode(xx, z, order, z.data[order / 2]);
                    }
                } else {
                    if (xx.data[order - 1] != 0) {
                        xx.data[order] = z.data[order / 2];
                        sort(xx, order);
                        for (int i = 0; i <= order; i++) {
                            x.data[i] = z.data[1 + (i + (order) / 2)];
                        }
                    } else {
                        insertdatainaninternalnode(xx, z, order, z.data[order / 2]);
                    }
                }
            }
        } else {
            z.childpointer[order + 1] = y;
            z.data[order] = y.data[0];
            sort(z, order);
        }
    }

    void Splitleafnodeandinsert(bpnode node, int order, int key) {

        bpnode y = new bpnode(order);

        if (node.parentpointer[0] == null) {
            bpnode z = new bpnode(order);
            int k = key;
            if (k % 2 == 0) {
                for (int i = 0; i <= order; i++) {
                    y.data[i] = node.data[i + (1 + (order / 2))];
                }
                for (int i = 0; i <= order; i++) {
                    node.data[i + (1 + order / 2)] = 0;
                }
            } else {
                for (int i = 0; i < order - 1; i++) {
                    y.data[i] = node.data[i + (order / 2)];
                }
                for (int i = 0; i <= order; i++) {
                    node.data[i + (order / 2)] = 0;
                }
                z.data[0] = node.data[order / 2];
            }
            node.next[0] = y;
            z.data[0] = y.data[0];
            z.childpointer[0] = node;
            z.childpointer[1] = y;
        } else {
            bpnode z = new bpnode(order);
            z = node.parentpointer[0];
            int k = key;
            if (k % 2 == 0) {
                for (int i = 0; i <= order; i++) {
                    y.data[i] = node.data[i + (1 + (order / 2))];
                }
                for (int i = 0; i <= order; i++) {
                    node.data[i + (1 + order / 2)] = 0;
                }
                insertdatainaninternalnode(z, y, order, key);
            } else {
                for (int i = 0; i < order - 1; i++) {
                    y.data[i] = node.data[i + (order / 2)];
                }
                for (int i = 0; i <= order; i++) {
                    node.data[i + (order / 2)] = 0;
                }
                insertdatainaninternalnode(z, y, order, key);

            }
            node.next[0] = y;
        }
    }

    public void sort(bpnode node, int deg) {
        for (int i = 0; i < deg - 1; i++) {
            for (int j = 0; j < deg - i - 1; j++) {
                if (node.data[j] > node.data[j + 1]) {
                    int temp = node.data[j];
                    node.data[j] = node.data[j + 1];
                    node.data[j + 1] = temp;
                }
            }
        }
    }

    public void findandinsert(bpnode node, int order, int key) {
        node.data[order] = key;
        sort(node, order);
    }

    public void insertatleaf(int order, Boolean isnonode) {
        bpnode cuurentnode = new bpnode(order);
        bpnode temp = new bpnode(order);
        if (isnonode = true) {
            bpnode node = new bpnode(order);
            System.out.println("Enter the key:");
            int key = input.nextInt();
            node.data[0] = key;
            bpnode head = new bpnode(order);
            head.next[0] = node;
        }

        else {
            System.out.println("Enter the key:");
            int key = input.nextInt();
            int i = 0;
            cuurentnode = head.next[0];
            temp = cuurentnode.next[0];
            while (cuurentnode.next[0] != null) {
                if ((cuurentnode.data[0] <= key) && (temp.data[0] >= key)) {
                    if (cuurentnode.data[order - 1] != 0) {
                        cuurentnode.data[order] = key;
                        sort(cuurentnode, order);
                        Splitleafnodeandinsert(cuurentnode, order, key);
                    } else {
                        findandinsert(cuurentnode, order, key);
                    }
                } else {
                    cuurentnode = cuurentnode.next[0];
                }
            }
        }
    }

}

class bptreeone extends operations {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        operations op = new operations();
        int choice;
        System.out.println("Enter the degree of the b+ tree: ");
        int degree = input.nextInt();
        do {

            System.out.println("Enter an operation to be performed: ");
            System.out.println("\n1)Insert \n2)Delete \n3)Search \n4)Exit");
            choice = input.nextInt();
            switch (choice) {
                case 1: {
                    int a = 0;
                    Boolean isnonode;
                    if (a >= 1) {
                        isnonode = true;
                        a++;
                    } else {
                        isnonode = false;
                    }
                    op.insertatleaf(degree, isnonode);
                }
                case 3:
                    op.display((2 * degree - 1));

            }
        } while (choice != 4);
    }

}