### laTeX
本质上讲，laTeX是提供meta数据编辑命令，然后编译生成目标格式文件如pdf，和MD(markdown language)类似。但是它包含丰富的公式，图表，
第三方插件，因而在编辑论文方面异常强大。<br>
[***plain-vanilla grammar***](https://www.overleaf.com/learn/latex/Learn_LaTeX_in_30_minutes)
#### overleaf
[***overleaf***](https://www.overleaf.com/project)是一个基于laTeX做文章排版的web编辑工具，提供很多类型paper的模板，例如ACM论文.
* 1.图表<br>
![Image of Curve](https://cdn.sharelatex.com/learn-scripts/images/4/43/Pgfplots4.png)<br>
可以参考[***Usage of Plot***](https://www.overleaf.com/learn/latex/Pgfplots_package)<br>
在线试用[Try It](https://www.sharelatex.com/project/new/template?zipUrl=/project/52e9d314f908c6363f001e44/download/zip&templateName=Data%20Plot%202d&compiler=pdflatex)
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

* 2.画图<br>
![Image of Formula](https://upload.wikimedia.org/wikipedia/commons/thumb/f/f7/Latex_example_text_formulas.png/600px-Latex_example_text_formulas.png)<br>
可以参考[***Usage of Picture***](https://www.overleaf.com/learn/latex/Picture_environment)

* 3.表格<br>
![Image of Table](https://cdn.sharelatex.com/learn-scripts/images/3/34/TablesEx12.png)<br>
可以参考[***Usage of Table***](https://www.overleaf.com/learn/latex/Tables)

* 4.颜色<br>
![Image of Color](https://cdn.sharelatex.com/learn-scripts/images/6/60/ColoursEx3.png)<br>
可以参考[***Usage of Color***](https://www.overleaf.com/learn/latex/Using_colours_in_LaTeX)

* 5.位置<br>
![Image of Position](https://cdn.sharelatex.com/learn-scripts/images/7/75/InsertingImagesEx4.png)<br>
可以参考[***Usage of Image***](https://www.overleaf.com/learn/latex/Inserting_Images)
