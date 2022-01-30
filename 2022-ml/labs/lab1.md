# Lab 1: End-to-end Machine Learning Project

This lab is based on Chapter 2 in the Géron book.  Be sure to ask for help when if you get stuck.  It uses the following dataset: [UCI Machine Learning Repository: Power consumption of Tetouan city Data Set](https://archive.ics.uci.edu/ml/datasets/Power+consumption+of+Tetouan+city).  If you prefer to try using a different dataset, you  may.  You will find the [code from Chapter 2](https://github.com/ageron/handson-ml) useful.

## Preparation

1. From your home directory, create a directory for your lab assignments, ml-labs and change into it.

```bash
mkdir ml-labs
cd ml-labs
```

Work from this directory to keep your code organized.  You may find it helpful to run Python in interactive mode when testing your code.  

Now create a subdirectory, `lab1` and change to it.

3. We can download our data as a ZIP file with `curl`.  By default, `curl` will output to `stdout`, but we can use `>` to redirect it to a file.

```bash
   curl https://archive.ics.uci.edu/ml/machine-learning-databases/00616/Tetuan%20City%20power%20consumption.csv > power.csv
```

To glance at the data, we can use VisiData , which is installed on lab machines. 

```bash
vd power.csv
```

Type `q` to quit when you're finished.

Now would be a good time to learn a terminal mode text editor (for example, `emacs` or `vi`), but lab computers also have the graphical but simple editor Atom installed.  Using an IDE for this assignment would likely be overkill.  See: [Emacs Keyboard Shortcuts](https://keycombiner.com/collections/emacs/).  

You can launch Emacs in terminal mode (if you want to force yourself to learn the shortcuts) with:

```bash
emacs -nw lab1.py
```

The `-nw` tells it not to open a graphical window.  You can launch it in graphical mode by leaving this off.  If you launch a graphical editor such as Atom or Emacs in windowing mode from the terminal, you'll likely want to run the program as a background process from the terminal with `&`.

```bash
atom lab1.py&
```

## Lab Assignment

1. Load the data into a `DataFrame`.
2. Drop the `DateTime` column and then drop any rows with missing values.
3. Examine the grid histograms of the variables.
4. Save at least one such histogram that seems interesting. 
   For example, if your `DataFrame` is named `power`, you can use:

```python
power.hist(column='Wind Speed')
```

5. Use `scatter_matrix` to check for correlations between attributes.  You can get a list of all of the attributes with `power.columns`.
6. Run a linear regression to predict one of the power consumption columns.  Try it with an without the other power consumption columns present.

## What to turn in

Turn in a short report (likely less than a page) as a PDF describing what you found when examining the data in (3-5) and the results of (6).  The report must have at least one plot from your lab.  You may use $\LaTeX$ or Markdown for creating this report. You can find a [lab report template](https://www.overleaf.com/latex/templates/lab-report-template/pqwpnrsxxbkj) on Overleaf.,



## Notes

- You will not use a Jupyter notebook.

- You will write your code in a `.py` file and run it from the command line.

- You will save your plots as files.

### Pearson Correlation

At one point in Chapter 2, (p. 58), we use  Pearson's correlation ($\rho$, or $r$ when applied to a sample) between two variables, which is a measure on $[-1,1]$, with $1$ indicating a perfect linear correlation and $-1$ a perfect inverse linear correlation.  It is a normalized measurement of covariance -- normalized because the denominator maps the covariance to $[-1,1]$. The covariance is defined as

$$
\rho_{X,Y}= \frac{\operatorname{cov}(X,Y)}{\sigma_X \sigma_Y}, \text{where}
$$

$$
\operatorname{cov}(X,Y)= \mathbb{E}\left[X Y\right] - \mathbb{E}\left[X\right] \mathbb{E}\left[Y\right].
$$

Given $x_i\in X$ and $y_i\in Y$ for all  $x_i, y_i$, the numerator (and thus the entire expression) will be highest when high $x_i$ values are paired with high $y_i$ values.

See [Pearson correlation coefficient - Wikipedia](https://en.wikipedia.org/wiki/Pearson_correlation_coefficient).

### Saving a Plot with Pandas

To save a Pandas plot, you use the following template. For example:

```python
my_plot = housing.plot(
 kind='scatter',
 x='median_income',
 y='median_house_value',
 alpha=0.1)
my_plot.savefig('./my_figure.pdf')
```

In general, in research settings, we want to save as an SVG file and convert to a PDF because vector graphics do not become pixelated when we zoom in on them.

Matplotlib and will automatically save to the correct file type.

To download the data, you can just use `curl` on the command line.
