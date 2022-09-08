#=
BWT:
- Julia version: 1.5.1
- Author: Sabarish
- Date: 2020-10-15
=#

#-------------------------
# Burrow Wheeler Transform
#-------------------------

print("Enter the word : ")
word = readline()               # Requesting the Sequence
println("Sequence : $word")

@time begin
function BWT(word)              # Function for finding BWT of a Sequence
    word = string(word,'$')     # Adding Dollar Sign to track last word
    l = length(word)
    T = []
    for i in 1:l
    push!(T,word)
    word = string(word[end],word[1:end - 1])   # Cyclic Rotations of the Sequence
    end
return T
end

function Bub_sort(list)         # Function for Sorting
    for i in 1:length(list)
    for j in 1:length(list)
    if list[i] < list[j]
        temp = list[i]
        list[i] = list[j]
        list[j] = temp
    end
    end
    end
    return list
end

function compress(BWT_sequence)
    final = ""
    count = 1
    for i in 2:length(BWT_sequence)
    if BWT_sequence[i] == BWT_sequence[i - 1]
        count += 1
    else
        final *= string(count) * string(BWT_sequence[i - 1])
        count = 1
    if i == length(BWT_sequence)
        final *= string(count) * string(BWT_sequence[i])
    end
    end
    end

    return final
end

function Expansion(short)
    expr = ""
    for i in 1:length(short)
    start = i
    while isnumeric(short[i])
        i += 1
        if isnumeric(short[i]) == false
            e = i
            counts = parse(Int,short[start:e - 1])
            for j in 1:counts
            expr = expr * short[i]
            end
        end
    end
    end
    return expr
end

List = BWT(word)
Lexicographical_order = Bub_sort(List)

BWT_sequence = ""
for i in 1:length(Lexicographical_order)
global BWT_sequence *= Lexicographical_order[i][end]  # Concatenation of last char of the Sequences
end

println("Burrow Wheeler Transform of $word is : $BWT_sequence ")

BWT_short = compress(BWT_sequence)

println("Short form of BWT sequence : $BWT_short ")

BWT_expand = Expansion(BWT_short)

println("Expansion of short form of BWT sequence : $BWT_expand")


#---------------------------------------------------------
# Inverse Burrow Wheeler Transform using Sort table Method
#---------------------------------------------------------

function IBWT(BWT_sequence) # Function to Find IBWT of a BWT Sequence
    BWT = []

    for i in 1:length(BWT_sequence)
    push!(BWT,BWT_sequence[i])
    end

    l = length(BWT)

    IBWT = Array{String}(undef,l) # Creating an Empty Array

    temp = BWT

    for i in 1:l
    temp_1 = copy(temp)
    temp_1 = Bub_sort(temp_1)
    for j in 1:l
    IBWT[j] = BWT[j] * temp_1[j] # Sorting and Concatenating
    end
    temp = IBWT
    end

    for i in 1:l
    IBWT[i] = IBWT[i][2:end]
    end

    return IBWT[1][2:end] # returning the First String of the list
end

IBWT_sequence = IBWT(BWT_expand)
println("Inverse Burrow Wheeler Transform of $BWT_sequence using sorted table method is : $IBWT_sequence ")

end