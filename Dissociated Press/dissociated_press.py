import sys

def listify(text):
  alist = []
  astr = ''
  awhat = split(text)
  while awhat[1] != '':
    alist += [awhat[0]]
    awhat = split(awhat[1])
  alist += [awhat[0]]
  return alist


def split(text):
  if text != '':
    astr = ''
    bstr = ''
    for letter in text:
      if letter != " " :
        astr += letter
      else:
        break

    i = 0
    for letter in text:
      if i <= len(astr):
        pass
      else:
        bstr += letter
      i += 1
    return (astr, bstr)

class Random:
    
    def __init__ (self, seed):
        self.rand = seed
        

    def next (self, range):
        self.rand = (7**5 * self.rand) % (2**31 - 1)
        return self.rand % range

    def choose (self, objects):
        return objects[self.next(len(objects))]




class Dissociated:

    def __init__ (self, words):
        self.list = listify(words)
        self.dic = {}
        i=0       
        while i < len(self.list):
            if self.list[i] in self.dic:
                self.dic[self.list[i]] += [self.list[i+1:]]  
            else:
                self.dic[self.list[i]] = [self.list[i+1:]]
            i += 1

    def run(self, seed):
      i = 0
      self.counter = 0
      rand = Random(seed)
      self.alist = self.list
      while (self.alist != []):
        self.show(self.alist[0])
        jump = rand.next(2)
        if jump == 1:
          random_list = rand.choose(self.dic[self.alist[0]])
          self.alist = random_list
        else:
          self.alist = self.alist[1:]
        i += 1
      self.finish()

               
    def show(self, word):
        if (self.counter+len(word)) <= 79: 
            sys.stdout.write(word + " ")
            self.counter += (len(word) + 1)
        else:
            sys.stdout.write('\n'+word + " ")
            self.counter = 0
            self.counter += ((len(word)) + 1)
                 
    def finish(self):
        if self.counter < 79: 
            sys.stdout.write('\n') 
        else:
          pass


diss =                                                         \
 Dissociated(                                                  \
  "it is a testament to the original computer hackers " +      \
  "prodigious skill that later programmers including " +       \
  "richard m stallman aspired to wear the same hacker " +      \
  "mantle. by the mid to late 1970s the term hacker had " +    \
  "acquired elite connotations. in a general sense a " +       \
  "computer hacker was any person who wrote software code " +  \
  "for the sake of writing software code. in the " +           \
  "particular sense, however it was a testament to " +         \
  "programming skill. like the term artist the meaning " +     \
  "carried tribal overtones. to describe a fellow " +          \
  "programmer as hacker was a sign of respect. to describe " + \
  "oneself as a hacker was a sign of immense personal " +      \
  "confidence. either way the original looseness of the " +    \
  "computer hacker appellation diminished as computers " +     \
  "became more common.")

 
diss.run(13)

        



