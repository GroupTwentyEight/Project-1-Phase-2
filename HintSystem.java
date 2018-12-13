import java.awt.*;

class HintSystem {

    HintSystem() {
    }

    // Start of the methods for the hint system.
    static String giveHint(int choice, Color[] palette, Color[] counterOfColors, int[][] graph) {
        int hintArray[] = new int[2];//use for result of the hints
        //Calls the hint command.Passes on both a color and a vertex.
        String answer = " ";
        hintArray = Hint(palette, counterOfColors, graph);
        //Error Checking. If it returns a value of zero, we alert the user that he needs to create a color palette.
        if (hintArray[0] == 0) {
			/*Assuming color values will be non-zero. This gives an error message
			if the player has not picked a color yet. */


            answer = "Please build the color palette before using the hints.";
	/*hintArray=101 only if the user needs to add more colors, or the graph is already fully colored. Encourages the user
	to keep thinking of more efficent coloring schemes.*/
        } else if (hintArray[0] == 101) {


            answer = "Think about how many colors you are using. Do you need more or less?";
        } else if (choice == 0) {
            //First hint, the small hint. It is somewhat vague.

            answer = "Use color " + hintArray[0];

        } else if (choice == 1) {
            //The big hint. Gives specific instructions as to how to color the graph.

            answer = "Use color " + hintArray[0] + " on vertex " + hintArray[1];
        }
        return answer;
    }

    private static int[] Hint(Color[] palette, Color[] counterOfColors, int[][] graph) {
//history is an arraylist of vertices the user has chosen
//palette is the number of colors available for the user
//counterOfColors records which color is assigned to each vertex
//choice indicates which hit button has been clicked
        int position = 1;
        //Creates a matrix where we store the vertex and associated color that will be used in the hint.
        int[] possibility = new int[2];
        int vertex;
        int index = 0;
        //Checks for uncolored vertices.
        while (counterOfColors[position] != null && position + 1 < counterOfColors.length) {
            position++;
        }
        vertex = position;
        if (counterOfColors[vertex] != null) {
            possibility[1] = vertex;
            possibility[0] = 101;
        } else {
            //creation of a new array to store the subset of all edges connected to that vertex.
            int[][] sub = new int[graph.length][2];
            //reading through rows
            int amount = 0;
            for (int j = 0; j < graph.length; j++) {
                if (vertex == graph[j][0] || vertex == graph[j][1]) {
                    sub[amount][0] = graph[j][0];
                    sub[amount][1] = graph[j][1];
                    amount++;
                }
            }
            //Creates an array that will store colors that have already been picked.
            Color[] pickedColors = new Color[counterOfColors.length * 2];

            //to get the colors that have been used
            for (int i = 0; i < amount; i++) {
                pickedColors[sub[i][0]] = counterOfColors[sub[i][0]];
                pickedColors[sub[i][1]] = counterOfColors[sub[i][1]];
            }
            //to get colors that have not been used for the vertex
            pickedColors[vertex] = palette[0];
            //If palette is empty, it will automatically skip the rest of the algorithm and return a value.
            if (palette[0] == null) {
                possibility[1] = vertex;
            } else {
                //If the palette is not empty, then it will find the color that is best suited for the vertex.
                //Have a counter to make the program double-check each solution.
                int count = 0;
                int k = 0;
                //Searching for any available colors for the vertex.
                while (count != 2) {
                    count++;
                    int m = 1;
                    while (m < pickedColors.length && pickedColors[vertex] != new Color(1, 1, 1)) {
                        //The conditional below is so that we do not double count the vertex.
                        if (m != vertex) {
                            if (pickedColors[vertex] == pickedColors[m] && k < palette.length - 1) {
                                k++;
                                pickedColors[vertex] = palette[k];
                                index = k;
                                count = 0;
                            } else if (pickedColors[vertex] == pickedColors[m] && k == palette.length - 1) {
                                pickedColors[vertex] = new Color(1, 1, 1);
                                index = 100;
                            }
                        }
                        m++;
                    }
                }

                possibility[1] = vertex;
                if (index == palette.length - 1 && index != 0) {
                    possibility[0] = index;
                } else {
                    possibility[0] = index + 1;

                }
            }
        }

        //Returns a vector with the vertex and the color that should be used to color it.
        return possibility;


    }
}
