### laTeX
�����Ͻ���laTeX���ṩmeta���ݱ༭���Ȼ���������Ŀ���ʽ�ļ���pdf����MD(markdown language)���ơ������������ḻ�Ĺ�ʽ��ͼ��
���������������ڱ༭���ķ����쳣ǿ��<br>
[***plain-vanilla grammar***](https://www.overleaf.com/learn/latex/Learn_LaTeX_in_30_minutes)
#### overleaf
[***overleaf***](https://www.overleaf.com/project)��һ������laTeX�������Ű��web�༭���ߣ��ṩ�ܶ�����paper��ģ�壬����ACM����.
* 1.ͼ��<br>
![Image of Curve](https://cdn.sharelatex.com/learn-scripts/images/4/43/Pgfplots4.png)<br>
���Բο�[***Usage of Plot***](https://www.overleaf.com/learn/latex/Pgfplots_package)<br>
��������[Try It](https://www.sharelatex.com/project/new/template?zipUrl=/project/52e9d314f908c6363f001e44/download/zip&templateName=Data%20Plot%202d&compiler=pdflatex)
```
\documentclass{article}
\usepackage[margin=0.5in]{geometry}
\usepackage[utf8]{inputenc}

\usepackage{pgfplots}
\pgfplotsset{width=8cm,compat=1.9}

\begin{tikzpicture}
\label{111}
\scalebox{}{}
\begin{axis}[
    title={Thread Calling Latency},
    xlabel={Thread Amount [Integer]},
    ylabel={Time [Milliseconds]},
    xmin=0, xmax=100,
    ymin=0, ymax=5,
    xtick={0,20,40,60,80,100},
    ytick={0.5,1,1.5,2,2.5,3,3.5,4,4.5,5},
    legend pos=north west,
    ymajorgrids=true,
    grid style=dashed,
]
 
\addplot[
    color=blue,
    mark=square,
    ]
    coordinates {
    (10,0.5)(20,0.8)(30,0.7)(40,0.8)(50,0.6)(60,0.6)(80,0.8)(90,0.5)
    };

\addplot[
    color=red,
    mark=square,
    ]
    coordinates {
    (10,1.1)(20,1.8)(30,1.7)(40,1.8)(50,1.6)(60,2.0)(80,1.9)(90,1.8)
    };
\legend{No DOMQL, DOMOQL Enabled}
 
\end{axis}
\end{tikzpicture}

\end{document}
```

* 2.��ͼ<br>
![Image of Formula](https://upload.wikimedia.org/wikipedia/commons/thumb/f/f7/Latex_example_text_formulas.png/600px-Latex_example_text_formulas.png)<br>
���Բο�[***Usage of Picture***](https://www.overleaf.com/learn/latex/Picture_environment)

* 3.���<br>
![Image of Table](https://cdn.sharelatex.com/learn-scripts/images/3/34/TablesEx12.png)<br>
���Բο�[***Usage of Table***](https://www.overleaf.com/learn/latex/Tables)

* 4.��ɫ<br>
![Image of Color](https://cdn.sharelatex.com/learn-scripts/images/6/60/ColoursEx3.png)<br>
���Բο�[***Usage of Color***](https://www.overleaf.com/learn/latex/Using_colours_in_LaTeX)

* 5.λ��<br>
![Image of Position](https://cdn.sharelatex.com/learn-scripts/images/7/75/InsertingImagesEx4.png)<br>
���Բο�[***Usage of Image***](https://www.overleaf.com/learn/latex/Inserting_Images)
