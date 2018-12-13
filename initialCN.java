class initialCN {
    static int vertices;
    static int[][] graph;


    initialCN(int v, int[][] g) {
        vertices = v;
        graph = g;
    }

    static int findCN() {
        int numOfColor = vertices;
/*Selects the vertex to color first.Loops so that the coloring of the graph begins at different nodes. It can 
have an effect on the chromatic number.*/
        for (int g = 1; g < vertices + 1; g++) {
            //Creates the list to keep track of color assignments
            int[] counterOfColors = new int[vertices + 1];
            //Creates a numerical representation of the color palette.
            int[] palette = new int[vertices];
            //Assigns the first color to the palette.
            palette[0] = 1;
            //Assigns the first color on the palette to the vertex.
            counterOfColors[g] = 1;
            int maxColor = 1;
	/*Position of the maximum color value. Makes it easier to get the value
	of the chromatic number without parsing the whole array*/
            int maxColorPosition = 0;
            for (int position = 1; position < vertices + 1; position++) {
                // Gets the maximum value of the color in the palette and its position.
                for (int i = 0; i < palette.length; i++) {
                    if (maxColor >= palette[i]) {
                    } else {
                        maxColor = palette[i];
                        maxColorPosition = i;
                    }
                }
                int location = 1;
                //Checks for an uncolored vertex.
                while (location < counterOfColors.length - 1 && counterOfColors[location] != 0) {
                    location++;
                }
			/*Since the length of the counterOfColors array equals the number 
			of vertices, the location indicates the next vertex to be colored.*/
                int vertex = location;
    		/*Creation of a new array to store the subset of all nodes 
    		connected to that point.*/
                int[][] sub = new int[graph.length][2];
                int amount = 0;
                for (int j = 0; j < graph.length; j++) {
                    //Row 0 is for u's and Row 1 is for v's
                    if (vertex == graph[j][0] || vertex == graph[j][1]) {
                        sub[amount][0] = graph[j][0];
                        sub[amount][1] = graph[j][1];
                        amount++;
                    } else {
                        amount = amount;
                    }
                }
                //Creates an array to store the colors that have been used.
                int[] pickedColors = new int[counterOfColors.length * 2];

                //To get the colors that have been used so far.
                for (int k = 0; k < amount; k++) {
                    pickedColors[sub[k][0]] = counterOfColors[sub[k][0]];
                    pickedColors[sub[k][1]] = counterOfColors[sub[k][1]];
                }
                //To get colors that have not been used yet.
                pickedColors[vertex] = palette[0];
                int count = 0;
                int q = 0;
                while (count != 2) {
                    count++;
                    int m = 1;
                    while (m < pickedColors.length) {
                        if (m != vertex) {
                            if (pickedColors[vertex] == pickedColors[m] && q < palette.length - 1) {
                                q++;
                                pickedColors[vertex] = palette[q];
                                count = 0;
                            } else if (pickedColors[vertex] == pickedColors[m] && q == palette.length - 1) {
                                pickedColors[vertex] = 101;
                            }

                        }
                        m++;
                    }

                }
         /*If all colors available have been used, creates a new color. 
         Otherwise, it assigns the color from the palette that is not in use in 
         this clique.*/
                if (pickedColors[vertex] == 101) {
                    maxColor++;
                    maxColorPosition++;
                    counterOfColors[vertex] = maxColor;
                    palette[maxColorPosition] = maxColor;
                } else {
                    counterOfColors[vertex] = pickedColors[vertex];
                }
            }

/*After it has looped through one vertex. It takes the chromatic number and 
compares it to the previous ones.*/
            for (int p = 0; p < palette.length; p++) {
                if (numOfColor > maxColor) {
                    numOfColor = maxColor;
                } else {
                    numOfColor = numOfColor;
                }
            }
        }
//Returns the chromatic number.
        return numOfColor;


    }

}
