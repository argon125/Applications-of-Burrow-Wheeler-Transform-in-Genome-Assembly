//Burrow Wheeler

// first part

//var word = window.prompt("Enter the string: ")+"$";
var word ="panama$"
l = word.length;
var lis = [];
var i;
for(i=0;i<l;i++){
  lis.push(word);
  word = word[l-1] + word.slice(0,l-1);
    }
    console.log(lis);

//bubble sort

function bubble_sort(seq){
  var bwt_seq;
  for(var i=0;i<seq.length-1;i++){
        for(var j=0;j<seq.length-1;j++){
            if(seq[j] > seq[j+1]){        
                bwt_seq = seq[j]  
                seq[j] = seq[j+1]
                seq[j+1] = bwt_seq
            }
        }
  }
  return seq
}

console.log("List in lexicographic order is : "+bubble_sort(lis))

// burrow wheeler function

function burrow_wheeler(seq){
    var bwt_str = ""
    for(i=0;i<seq.length;i++){
        bwt_str+=(seq[i][seq.length-1])
    }
    return bwt_str
}

bwtseq = burrow_wheeler(lis)
console.log("Burrow Wheeler transform of "+word.slice(0,l-1)+" yields: "+bwtseq)

// compression

function compress(seq){
    comp = ""
    count = 1
    for(var i=1;i<seq.length;i++){
        if(seq[i]==seq[i-1])
            count += 1
        else{
            comp += String(count) + seq[i-1]
            count = 1
          }
        if(i==seq.length-1)
            comp += String(count) + seq[i]
      }
            
    return comp
  }

compressed = compress(bwtseq)
console.log("Compressed BWT of "+bwtseq+" yields: "+compressed)
console.log("Length of Compressed Sequence: "+(compressed.length))

//expansion

function isdigit(myString) {
  return /\d/.test(myString);
}

function expand(comp_str){
    exp = ""
    count = 0
    start_ind = 0
    end_ind = 0
    
    for(var i=0;i<comp_str.length;i++){
        start_ind = i
        while(isdigit(comp_str[i])==true){
            i += 1
            if(isdigit(comp_str[i])==false){
                end_ind = i
                count = comp_str.slice(start_ind,end_ind)
                //print(f"Count {count}")
                for(var j=0;j<count;j++){
                    exp += comp_str[i]
                  }
              }
          }
      }
    return exp
  }

expanded_str = expand(compressed)
console.log("Expanded Version of "+compressed+" gives: "+expanded_str)
console.log("Length of expanded sequence is : "+expanded_str.length)

// Inverse Burrow Wheeler

function linsearch(seq,char,ind_arr){
    ind = -1                                 //incase not found
    for(i=0;i<seq.length;i++){
        if(seq[i] == char && ind_arr.includes(i)==false){
            ind = i
            break;
        }
    }
            
    return ind
  }
  
function zeros(n) {
    var list = [];

    for (var i = 0; i < n; i++) {
        list.push(0)
    }

    return list;
}
  var sortAlphabets = function(text) {
    return text.split('').sort();
};
function inv_burrow_wheeler(bwt_seq){
    console.log("Burrow wheeler : "+bwt_seq)
    ind = linsearch(bwt_seq,"$",[])
    console.log("Index  = "+ind)
    chars = sortAlphabets(bwt_seq)
    console.log("Chars : "+chars)
    n = bwt_seq.length
    recons_seq = ""
    traversed_indexes = zeros(n)
    for(i=0;i<n;i++){
        recons_seq += chars[ind]
        console.log("Reconstructed sequence: "+recons_seq)
        traversed_indexes[i] = ind
        ind = linsearch(bwt_seq,chars[ind],traversed_indexes)
        console.log("Index  = "+ind)
      }
        
    return recons_seq
  }
  
  console.log("Inverse Burrow Wheeler transform of "+bwtseq+" yields:"+ inv_burrow_wheeler(bwtseq)+"$")
  