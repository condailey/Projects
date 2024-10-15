#Inspired by a tutorial. This program taught me a simple automation script that makes the otherwise tedious task of merging PDFs very easy.#

import PyPDF2
import sys
import os

merger = PyPDF2.PdfMerger()

pdfs = ['pdf1.pdf', 'pdf2.pdf']

for pdf in pdfs:
    merger.append(pdf)
    merger.write("combinedDocs.pdf")
