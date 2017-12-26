package com.github.syuchan1005.npmscriptrunner;

import com.intellij.execution.lineMarker.ExecutorAction;
import com.intellij.execution.lineMarker.RunLineMarkerContributor;
import com.intellij.icons.AllIcons;
import com.intellij.json.psi.JsonProperty;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class NpmRunLineMarkerContributor extends RunLineMarkerContributor {
	@Nullable
	@Override
	public Info getInfo(@NotNull PsiElement psiElement) {
		if (psiElement instanceof JsonProperty) {
			PsiElement parent = psiElement.getParent().getParent();
			if (parent instanceof JsonProperty && ((JsonProperty) parent).getName().equals("scripts")) {
				String text = psiElement.getFirstChild().getText();
				final String script = text.substring(1, text.length() - 1);
				return new Info(AllIcons.General.Run,
						(element) -> "Run '" + script + "'\nDebug '" + script + "'",
						ExecutorAction.getActions());
			}
		}
		return null;
	}

	public static void main(String[] args) {

	}
}
