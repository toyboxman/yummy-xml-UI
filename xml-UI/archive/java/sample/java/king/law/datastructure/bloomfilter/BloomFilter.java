package king.law.datastructure.bloomfilter;

//布隆过滤器（Bloom Filter）是一个很长的二进制向量和一系列随机映射函数。
//布隆过滤器可以用于检索一个元素是否在一个集合中。
//它的优点是空间效率和查询时间都比一般的算法要好的多，缺点是有一定的误识别率和删除困难。

import com.google.common.hash.Funnels;

/**
 * @see <a href="https://www.zhihu.com/question/389604738">Bloom Filter</a>
 * @see <a href="https://zhuanlan.zhihu.com/p/405873905">Bloom Filter-Guava</a>
 */
public class BloomFilter {
    static com.google.common.hash.BloomFilter<Integer> bloomFilter_default =
            // 后边两个参数：预计包含的数据量，和允许的误差值
            com.google.common.hash.BloomFilter.create(
                    Funnels.integerFunnel(),
                    100000,
                    0.01);

    public static void main(String[] args) {
        BloomFilter filter_0 = new BloomFilter();
        for (int i = 0; i < 100000; i++) {
            filter_0.bloomFilter_default.put(i);
        }

        System.out.println(filter_0.bloomFilter_default.mightContain(1));
        System.out.println(filter_0.bloomFilter_default.mightContain(2));
        System.out.println(filter_0.bloomFilter_default.mightContain(3));
        System.out.println(filter_0.bloomFilter_default.mightContain(100001));

        //可以把布隆过滤器通过 bloomFilter.writeTo() 写入一个文件，放入OSS、S3这类对象存储中
        //bloomFilter.writeTo();

    }
}
