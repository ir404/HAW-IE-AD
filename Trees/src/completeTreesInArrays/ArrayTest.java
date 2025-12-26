package completeTreesInArrays;

public class ArrayTest {
    public void testArrayTree() {
        Tree myTree = new Tree(10);
        int k = 4;
        System.out.println("Tree length: " + myTree.getLength());
        System.out.println("parent of " + k + " is " + myTree.parent(k));
        System.out.println("left of " + k + " is " + myTree.left(k));
        System.out.println("right of " + k + " is " + myTree.right(k));
        System.out.println("Does " + k + " exist? " + myTree.searchFor(k));
    }
}
