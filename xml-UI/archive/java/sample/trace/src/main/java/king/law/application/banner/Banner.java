package king.law.application.banner;

import king.law.application.utils.RenderUtil;
import org.apache.commons.io.IOUtils;
import org.crsh.text.Color;
import org.crsh.text.Decoration;
import org.crsh.text.ui.TableElement;

import java.io.IOException;
import java.util.Objects;

import static org.crsh.text.ui.Element.label;

public class Banner {
    private static String LOGO = "Welcome to Arthas";
    public String getBanner() {
        String logoText = null;
        try {
            logoText = IOUtils.toString(Objects.requireNonNull(Banner.class.getResourceAsStream("/king/law/application/banner/banner.txt")));
            StringBuilder sb = new StringBuilder();
            String[] LOGOS = new String[6];
            int i = 0, j = 0;
            for (String line : logoText.split("\n")) {
                sb.append(line);
                sb.append("\n");
                if (i++ == 4) {
                    LOGOS[j++] = sb.toString();
                    i = 0;
                    sb.setLength(0);
                }
            }

            TableElement logoTable = new TableElement();
            logoTable.row(label(LOGOS[0]).style(Decoration.bold.fg(Color.red)),
                    label(LOGOS[1]).style(Decoration.bold.fg(Color.yellow)),
                    label(LOGOS[2]).style(Decoration.bold.fg(Color.cyan)),
                    label(LOGOS[3]).style(Decoration.bold.fg(Color.magenta)),
                    label(LOGOS[4]).style(Decoration.bold.fg(Color.green)),
                    label(LOGOS[5]).style(Decoration.bold.fg(Color.blue)));
            LOGO = RenderUtil.render(logoTable);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return LOGO;
    }
}
