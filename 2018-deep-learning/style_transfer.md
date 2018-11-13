CS Seminar in Deep Computer Vision and Language

is difference.Ursinus College

Alvin Grissom II

# Exercise: Neural Style Transfer

## 

## Introduction

What would a photo of you look like if it were drawn by Picasso or Van Gogh?   

For this assignment, you will dabble in a technique called **neural style transfer**.  This allows one to convert an image into the "style" of another image.  Like text generation, this can be seen as a kind of "computational creativity."

This can be done using most of the tools we have developed so far in the semester.  Recall that convolutional neural networks make use of a number of **filters** which learn various properties of images used for classification.  Intuitively, we tend to think of each of these filters as learning some property of the image.  One layer may learn to look for outlines, another might learn to look for eyes, and so on.  Also recall that these filters are entirely learned.  We don't typically design a filter to learn to look for outlines; the network, by contrast, learns to do this.  We can visualize what the filters in the pretrained VGG19 learn in `conv_filter_visualization.py` from the Keras examples repository.

## How it works

Neural style transfer can work with the filters from any trained CNN.  We define two kinds of loss: **style loss** and **content loss**.  Out total loss is simply the sum of the style loss and the content loss.

The content loss uses activations from the layers closer to the input, which tend to capture local information, and the style loss uses activations from the layer immediately prior to the output, which sohuld theoretically have the most "general" information about the image.  After the transformation, the CNN should still be able to identify the same objects as before, but it should do so after performing various transformations.

We calculate the similarity between two images by comparing their respective **Gram matrices**.  The Gram matrix is just the transpose of a matrix multiplied by itself: M<sup>T</sup>M.  The result of this is that every row is multiplied by every column, "spreading out" the global information about the matrix.  We can then just calculate the MSE between the two matrices.  

We then minimize this difference with standard backpropagation techniques.

## Assignment

This assignment is intended to be a fun exercise to give you a chance to experiment with style transfer.

To start, we'll use `neural_style_transfer.py`.

Run the program.  You'll see that it requires three arguments: `base`, `ref`, and `res_prefix`, referring to the image you're starting with, the image whose style you're transferring, and the prefix of the new, generated file, respectively.

1. Try running transferring the style of van Gogh's "Starry Night" to a photo of the Ursinus campus.   You can find the images here:

https://media.overstockart.com/optimized/cache/data/product_images/VG485-1000x1000.jpg

https://ctcl.org/wp-content/uploads/2015/08/Ursinus-Profile-Photo.2018.jpg

Try the same with one of Picasso's paintings.

2. There are other parameters, as well.  In particular, try adjusting the content and style weight parameters.  These are weights on the loss function that determines how much of the content vs. the style to preserve.
3. Try the same task with at least three other images (perhaps a personal photo?) and three other style sources and submit your results in a PDF with some basic qualitative analysis.    This does not need to be long.
4. 