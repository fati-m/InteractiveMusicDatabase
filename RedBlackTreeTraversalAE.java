// --== CS400 Spring 2023 File Header Information ==--
// Name: Matthew Wang
// Email: mewang@wisc.edu
// Team: AN Blue
// TA: Gary Dahl
// Lecturer: Gary Dahl
// Notes to Grader: uwu :3

/**
 * Red-Black Tree implementation with a Node inner class for representing
 * the nodes of the tree. Currently, this implements a Binary Search Tree that
 * we will turn into a red black tree by modifying the insert functionality.
 * In this activity, we will start with implementing rotations for the binary
 * search tree insert algorithm.
 */
public class RedBlackTreeTraversalAE<T extends Comparable<T>> extends RedBlackTreeAE<T> implements SortedCollectionInterface<T>, RedBlackTreeTraversalInterfaceAE<T> {

    /**
     * Removes the value data from the tree if the tree contains the value.
     * This method will not attempt to rebalance the tree after the removal and
     * should be updated once the tree uses Red-Black Tree insertion.
     *
     * @return true if the value was remove, false if it didn't exist
     * @throws NullPointerException     when the provided data argument is null
     * @throws IllegalArgumentException when data is not stored in the tree
     */
    @Override
    public boolean remove(T data) throws NullPointerException, IllegalArgumentException {
        // null references will not be stored within this tree
        if (data == null) {
            throw new NullPointerException("This RedBlackTree cannot store null references.");
        } else {
            Node<T> nodeWithData = this.findNodeWithData(data);
            // throw exception if node with data does not exist
            if (nodeWithData == null) {
                throw new IllegalArgumentException("The following value is not in the tree and cannot be deleted: " + data.toString());
            }

            boolean hasRightChild = (nodeWithData.context[2] != null);
            boolean hasLeftChild = (nodeWithData.context[1] != null);

            if (hasRightChild && hasLeftChild) {
                // has 2 children
                Node<T> successorNode = this.findMinOfRightSubtree(nodeWithData);
                // replace value of node with value of successor node
                nodeWithData.data = successorNode.data;
                // remove successor node
                if (successorNode.context[2] == null) {
                    if (successorNode.blackHeight == 0) {
                        // successor has no children, replace with null
                        this.replaceNode(successorNode, null);
                    } else {
                        Node<T> doubleBlack = new Node<>(null);
                        doubleBlack.blackHeight = 2;
                        this.replaceNode(successorNode, doubleBlack);
                        enforceRBTreePropertiesAfterRemove(doubleBlack);
                    }
                } else {// successor has a right child, replace successor with its child
                    successorNode.context[2].blackHeight += successorNode.blackHeight;
                    this.replaceNode(successorNode, successorNode.context[2]);
                    if (successorNode.context[2].blackHeight == 2) {
                        enforceRBTreePropertiesAfterRemove(successorNode.context[2]);
                    }
                }
            } else if (hasRightChild) {
                // only right child, replace with right child
                nodeWithData.context[2].blackHeight += nodeWithData.blackHeight;
                this.replaceNode(nodeWithData, nodeWithData.context[2]);
                if (nodeWithData.context[2].blackHeight == 2) {
                    enforceRBTreePropertiesAfterRemove(nodeWithData.context[2]);
                }
            } else if (hasLeftChild) {
                // only left child, replace with left child
                nodeWithData.context[1].blackHeight += nodeWithData.blackHeight;
                this.replaceNode(nodeWithData, nodeWithData.context[1]);
                if (nodeWithData.context[1].blackHeight == 2) {
                    enforceRBTreePropertiesAfterRemove(nodeWithData.context[1]);
                }

            } else {
                // no children, replace node with a null node
                if (nodeWithData.blackHeight == 0) {
                    this.replaceNode(nodeWithData, null);
                } else {
                    Node<T> doubleBlack = new Node<>(null);
                    doubleBlack.blackHeight = 2;
                    this.replaceNode(nodeWithData, doubleBlack);
                    enforceRBTreePropertiesAfterRemove(doubleBlack);
                }
            }
            this.size--;
            return true;
        }
    }

    /**
     * This method resolves any red-black tree property violations that are
     * introduced by removing each new node into a red-black tree.
     *
     * @param checkNode, the (sometimes double black) node removed / causing a violation in the tree
     */
    public void enforceRBTreePropertiesAfterRemove(Node<T> checkNode) {

        // checking if checkNode, the parameter, is the root
        if (checkNode.equals(root)) {
            if (checkNode.data == null) {
                root = null;
            } else {
                checkNode.blackHeight = 1;
            }
        } else {
            // set sibling (will be used in various parts)
            if (checkNode.context[0] != null) {
                Node<T> sibling = null;
                // if the checkedNode is on the right, it's sibling is on the left
                if (checkNode.isRightChild() && checkNode.context[0].context[1] != null) {
                    sibling = checkNode.context[0].context[1];

                    // vice versa
                } else if (!checkNode.isRightChild() && checkNode.context[0].context[2] != null) {
                    sibling = checkNode.context[0].context[2];
                }

                // sibling is black
                if (sibling != null && sibling.blackHeight == 1) {
                    Node<T> placeholder;
                    if ((sibling.context[2] != null && sibling.context[2].blackHeight == 0) && (sibling.context[1] != null && sibling.context[1].blackHeight == 0)) {

                        // both sibling's children are red
                        if (sibling.isRightChild()) {
                            sibling.context[2].blackHeight = 1;
                        } else {
                            sibling.context[1].blackHeight = 1;
                        }
                        rotate(sibling, checkNode.context[0]);

                        // fix double black
                        if (checkNode.data == null) {
                            if (checkNode.isRightChild()) {
                                checkNode.context[0].context[2] = null;
                            } else {
                                checkNode.context[0].context[1] = null;
                            }
                        } else {
                            checkNode.blackHeight = 1;
                        }
                    }

                    // CASE 2: if the sibling is red, do case 2
                    else if (sibling.context[2] != null && sibling.context[2].blackHeight == 0) {
                        if (!sibling.isRightChild()) {
                            sibling.blackHeight = 1;
                            sibling.context[2].blackHeight = 1;
                            placeholder = sibling.context[2];
                            rotate(sibling.context[2], sibling);
                            sibling = placeholder;
                        }
                        // rotate
                        rotate(sibling, checkNode.context[0]);

                        // reset references
                        if (checkNode.data == null) {
                            if (checkNode.isRightChild()) {
                                checkNode.context[0].context[2] = null;
                            } else {
                                checkNode.context[0].context[1] = null;
                            }
                        } else {
                            checkNode.blackHeight = 1;
                        }
                    }

                    // EXTENSION OF CASE 2: (SIBLING IS RED AND HAS A LEFT CHILD)
                    else if (sibling.context[1] != null && sibling.context[1].blackHeight == 0) {
                        if (sibling.isRightChild()) {
                            sibling.blackHeight = 1;
                            sibling.context[1].blackHeight = 1;
                            placeholder = sibling.context[1];
                            rotate(sibling.context[1], sibling);
                            sibling = placeholder;
                        }
                        rotate(sibling, checkNode.context[0]);

                        // resolve the double black error
                        if (checkNode.data == null) {
                            if (checkNode.isRightChild()) {
                                checkNode.context[0].context[2] = null;
                            } else {
                                checkNode.context[0].context[1] = null;
                            }
                        } else {
                            checkNode.blackHeight = 1;
                        }
                    }

                    // CASE 3: sibling's child(ren) are not red
                    else {

                        // resolve double black error
                        if (checkNode.data == null) {
                            if (checkNode.isRightChild()) {
                                checkNode.context[0].context[2] = null;
                            } else {
                                checkNode.context[0].context[1] = null;
                            }
                        } else {
                            checkNode.blackHeight = 1;
                        }

                        // recolor (if the parent was red, we're done, if it was black, there is another violation)
                        sibling.blackHeight = 0;
                        checkNode.context[0].blackHeight++;

                        //fix double black
                        if (checkNode.context[0].blackHeight == 2) {
                            enforceRBTreePropertiesAfterRemove(checkNode.context[0]);
                        }
                    }
                }
            }

        }
    }

    /**
     * Getter method for the root of the tree (used by BD)
     */
    public Node<T> getRoot() {
        return this.root;
    }

}
