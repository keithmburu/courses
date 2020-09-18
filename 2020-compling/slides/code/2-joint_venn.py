import matplotlib.pyplot as plt
from itertools import combinations
from simple_venn import venn2, venn3, venn4


#fig, axes = plt.subplots(1, 3, figsize=(24, 8))
sets = 'A B'.split()
subsets = [' and '.join(combo) for i in range(1, 3) for combo in combinations(sets, i)]
#ax = axes[0]
venn2(subsets)
#ax.set_title('venn2', fontsize=24)
plt.savefig('joint.png')
