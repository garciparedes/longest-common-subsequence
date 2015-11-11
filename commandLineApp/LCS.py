
#!/usr/bin/env python

'''
LCS example 
'''
__author__ =  'Sergio García Prado'
__version__=  '1.0'

def max(a, b):
    return a if (a > b) else b


def lcs(X, Y, m, n):
    """
    LCS Algorithm

    The Algorithm returns the Longest Common Subsequence of X and Y.

    @type  X: list
    @param X: The First Sequence.

    @type  Y: list
    @param Y: The Second Sequence.

    @type  m: number
    @param m: Len of First Sequence.

    @type  X: number
    @param X: Len of Second Sequence.

    @rtype:   list
    @return:  the Longest Common Subsequence of two input Sequences.
    """

    L = [[0 for col in range(n+1)] for row in range(m+1)]

    for i in range(1, m+1):
        for j in range(1, n+1):
            if (X[i-1] ==Y[j-1]):
                L[i][j] = L[i-1][j-1] + 1
            else:
                L[i][j] = max(L[i-1][j], L[i][j-1])

    #return L[m][n]
    index = L[m][n]
    lcs = list(" " * index)
    i = m
    j = n
    while(i > 0 and j > 0):
       if (X[i-1] == Y[j-1]):
           lcs[index-1] = X[i-1]
           i -= 1
           j -= 1
           index -= 1
       elif (L[i-1][j] > L[i][j-1]):
           i -= 1
       else:
           j -= 1

    return "".join(lcs)



def main():
    """
    Main method.

    It initializes the app. Also acts as IO.

    If input is none it uses
    ACCGGTGGACAATTCA as X and
    GGAAAGAGATATGCAC as Y

    Then rerurn LCS of it.
    """
    X = "ACCGGTGGACAATTCA";
    Y = "GGAAAGAGATATGCAC";

    Xinput = raw_input('Introduzca la primera secuencia:')
    Yinput = raw_input('Introduzca la segunda secuencia:')

    if(Xinput != ""):
    	X = Xinput

	if(Yinput != ""):
		Y = Yinput

    m = len(X);
    n = len(Y);

    print "La primera secuencia es: %s" % (X)

    print "La segunda secuencia es: %s" % (Y)

    l = lcs( X, Y, m, n )

    print"LCS es '%s' y su longitud es %s" % (l, len(l) )

if __name__ == "__main__":
    main()
